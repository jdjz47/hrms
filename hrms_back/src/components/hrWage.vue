<template>
  <div>
    <el-switch
        v-model="value"
        active-text="部门薪酬"
        inactive-text="员工薪资"
        @change="changeShow"
    >
    </el-switch>
    <div v-show="value">
      <el-button @click="addRegular" style="margin-top: 50px;margin-bottom: 20px;margin-left: 550px" type="primary" round>添加薪资标准</el-button>
      <el-table
          :data="regularList"
          border
          style="width: 60%;margin-left: 350px">
        <el-table-column
            fixed
            prop="name"
            label="部门名称"
            width="150">
        </el-table-column>
        <el-table-column
            prop="salary"
            label="工资标准"
            width="120">
        </el-table-column>
        <el-table-column
            label="奖金标准"
            width="120">
          <template slot-scope="scope">
            {{scope.row.reward}}个月
          </template>
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="100">
          <template slot-scope="scope">
            <el-button @click="updateClick(scope.row.id)" type="text" size="small">编辑</el-button>
            <el-button type="text" @click="deleteStanded(scope.row.id)" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="block">
        <span class="demonstration">完整功能</span>
        <el-pagination
            @size-change="handleRegularSizeChange"
            @current-change="handleRegularCurrentChange"
            :current-page="regularCurrent"
            :page-sizes="[1, 2, 5,10]"
            :page-size="regularSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="regularTotal">
        </el-pagination>
      </div>
    </div>
    <div v-show="!value" style="">
      <el-button style="margin-left: 450px" @click="test" type="primary">导出Execl表格</el-button>
      <el-button type="primary" @click="SendMail">一键发送邮件</el-button>
      <el-button type="primary" @click="keepWage">保存工资条</el-button>
      <h3 style="margin-right: 1000px">员工出勤情况</h3>
    <div v-for="(p,index) in info" v-bind:key="index">
      <el-progress style="width:450px;margin-left: 200px;" :text-inside="true" :show-text="true" :stroke-width="24" :percentage="p.number"></el-progress>
    <div style="margin-right: 1200px">{{p.name}}</div>
    </div>
      <el-table
          :data="info"
          style="width: 80%;margin-top:110px;margin-left: 250px"
          max-height="250">
        <el-table-column
            fixed
            prop="name"
            label="姓名"
            width="150">
        </el-table-column>
        <el-table-column
            prop="salary"
            label="日薪"
            width="120">
        </el-table-column>
        <el-table-column
            prop="reward"
            label="奖金"
            width="120">
        </el-table-column>
        <el-table-column
            prop="totalWage"
            label="总工资"
            width="120">
        </el-table-column>
        <el-table-column
            prop="fact"
            label="实发工资"
            width="300">
        </el-table-column>
        <el-table-column
            prop="department"
            label="所属部门"
            width="120">
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="120">
          <template slot-scope="scope">
            <el-button
                @click="deleteRow(scope.row.id)"
                type="text"
                size="small">
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="block">
        <span class="demonstration">完整功能</span>
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="current"
            :page-sizes="[1,2,5,10]"
            :page-size="size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import store from "@/assets/Store";

export default {
  name: "hrWage",
  data() {
    return {
      value: false,
      info:[],
      current:0,
      size:4,
      total:0,
      list:[],
      departmentList:[],
      companyId:'',
      regularSize:4,
      regularCurrent:0,
      regularList:[],
      regularTotal:0
    }
  },
  created() {
    let departmentList;
    axios.post('http://' + store.baserUrl + '/hrms_regular/regular/departmentInfo')
        .then(response => {
          this.departmentList=response.data.data.list;
          departmentList=response.data.data.list;
          this.companyId=response.data.data.list[0].companyId;
          console.log(response.data);
        })
        .catch();
    let array=[];
    axios.post('http://' + store.baserUrl + '/hrms_employee/employee/hrDepartment',{'current':this.current,'size':this.size})
        .then(response => {
          this.list = response.data.data.list;
          this.total = Number(response.data.data.total);
          this.list.map(function (item) {
          //出勤比例天数
          let number=Math.ceil(item.dayTime/240*100);
          //日薪资
          let salarys=item.salary/20;
          //奖金prize
          let reward;
          console.log(number)
            if(number==100){
            //有奖金
              departmentList.forEach((p) => {
                if(p.name==item.department){
                  reward=p.reward*item.salary;
                }
              });

          }else{
            //没有奖金
              reward=0;
          }
            //总工资
            let totalWage=item.dayTime*salarys+reward;
            //实际应发工资
            let fact=0;
            if(totalWage<5000){
              fact=totalWage;
            }else if(8000>totalWage>5000){
              fact=totalWage-totalWage*3/100
            }else{
              fact=totalWage-totalWage*10/100
            }

            array.push({'id':item.id,'name':item.name,'number':number,'salary':salarys,'reward':reward,'totalWage':totalWage,'fact':fact,'department':item.department});
          })
          console.log(array)
          this.info=array;
          console.log(this.info)
        })
        .catch();
  },
  methods:{
    SendMail(){
      console.log(this.info)
      axios.post('http://' + store.baserUrl + '/hrms_employee/employee/SendMail',{'list':this.info})
          .then(response => {
            console.log(response.data.msg);
            console.log(response.data);
          })
          .catch();
    },
    handleRegularSizeChange(val){
      this.regularSize=val;
      axios.post('http://' + store.baserUrl + '/hrms_regular/regular/InfoAll',{'size':this.regularSize,'current':this.regularCurrent})
          .then(response => {
            console.log(response.data);
            this.regularList=response.data.data.list;
            this.regularTotal=response.data.data.total;
          })
          .catch();
    },
    handleRegularCurrentChange(val){
      this.regularCurrent=val;
      axios.post('http://' + store.baserUrl + '/hrms_regular/regular/InfoAll',{'size':this.regularSize,'current':this.regularCurrent})
          .then(response => {
            console.log(response.data);
            this.regularList=response.data.data.list;
            this.regularTotal=response.data.data.total;
          })
          .catch();
    },
    updateClick(id){
    this.$router.push({path:'/backPage/updateRegular/'+id});
    },
    deleteStanded(id){
      axios.post('http://' + store.baserUrl + '/hrms_regular/regular/deleteStanded',{'id':id})
          .then(response => {
            console.log(response.data);
            this.$message.success("删除成功")
            axios.post('http://' + store.baserUrl + '/hrms_regular/regular/InfoAll',{'size':this.regularSize,'current':this.regularCurrent})
                .then(response => {
                  console.log(response.data);
                  this.regularList=response.data.data.list;
                  this.regularTotal=response.data.data.total;
                })
                .catch();
          })
          .catch();
    },
    deleteRow(id){
      axios.post('http://' + store.baserUrl + '/hrms_employee/employee/deleteEmployee',{'id':id})
          .then(response => {
            console.log(response.data)
            this.$message.success("删除成功");
            let departmentList;
            axios.post('http://' + store.baserUrl + '/hrms_regular/regular/departmentInfo')
                .then(response => {
                  this.departmentList=response.data.data.list;
                  departmentList=response.data.data.list;
                  this.companyId=response.data.data.list[0].companyId;
                  console.log(response.data);
                })
                .catch();
            let array=[];
            axios.post('http://' + store.baserUrl + '/hrms_employee/employee/hrDepartment',{'current':this.current,'size':this.size})
                .then(response => {
                  this.list = response.data.data.list;
                  this.total = Number(response.data.data.total);
                  this.list.map(function (item) {
                    //出勤比例天数
                    let number=Math.ceil(item.dayTime/240*100);
                    //日薪资
                    let salarys=item.salary/20;
                    //奖金prize
                    let reward;
                    console.log(number)
                    if(number==100){
                      //有奖金
                      departmentList.forEach((p) => {
                        if(p.name==item.department){
                          reward=p.reward*item.salary;
                        }
                      });

                    }else{
                      //没有奖金
                      reward=0;
                    }
                    //总工资
                    let totalWage=item.dayTime*salarys+reward;
                    //实际应发工资
                    let fact=0;
                    if(totalWage<5000){
                      fact=totalWage;
                    }else if(8000>totalWage>5000){
                      fact=totalWage-totalWage*3/100
                    }else{
                      fact=totalWage-totalWage*10/100
                    }

                    array.push({'id':item.id,'name':item.name,'number':number,'salary':salarys,'reward':reward,'totalWage':totalWage,'fact':fact,'department':item.department});
                  })
                  console.log(array)
                  this.info=array;
                  console.log(this.info)
                })
                .catch();
          })
          .catch();
    },
    changeShow(){
      //false:员工,true:部门
      if(this.value){
        console.log("部门")
        axios.post('http://' + store.baserUrl + '/hrms_regular/regular/InfoAll',{'size':this.regularSize,'current':this.regularCurrent})
            .then(response => {
              console.log(response.data);
              this.regularList=response.data.data.list;
              this.regularTotal=response.data.data.total;
            })
            .catch();
      }else{
        let departmentList;
        axios.post('http://' + store.baserUrl + '/hrms_regular/regular/departmentInfo')
            .then(response => {
              this.departmentList=response.data.data.list;
              departmentList=response.data.data.list;
              this.companyId=response.data.data.list[0].companyId;
              console.log(response.data);
            })
            .catch();
        let array=[];
        axios.post('http://' + store.baserUrl + '/hrms_employee/employee/hrDepartment',{'current':this.current,'size':this.size})
            .then(response => {
              this.list = response.data.data.list;
              this.total = Number(response.data.data.total);
              this.list.map(function (item) {
                //出勤比例天数
                let number=Math.ceil(item.dayTime/240*100);
                //日薪资
                let salarys=item.salary/20;
                //奖金prize
                let reward;
                console.log(number)
                if(number==100){
                  //有奖金
                  departmentList.forEach((p) => {
                    if(p.name==item.department){
                      reward=p.reward*item.salary;
                    }
                  });

                }else{
                  //没有奖金
                  reward=0;
                }
                //总工资
                let totalWage=item.dayTime*salarys+reward;
                //实际应发工资
                let fact=0;
                if(totalWage<5000){
                  fact=totalWage;
                }else if(8000>totalWage>5000){
                  fact=totalWage-totalWage*3/100
                }else{
                  fact=totalWage-totalWage*10/100
                }

                array.push({'id':item.id,'name':item.name,'number':number,'salary':salarys,'reward':reward,'totalWage':totalWage,'fact':fact,'department':item.department});
              })
              console.log(array)
              this.info=array;
              console.log(this.info)
            })
            .catch();
      }
    },
    keepWage(){
      this.$router.push({path:'/backPage/uploadAndKeep/'+this.companyId});
    },
    test(){
      axios.post('http://' + store.baserUrl + '/hrms_employee/employee/ImportData',{'list':this.info})
          .then(response => {
            console.log(response.data.msg);
            console.log(response.data);
          })
          .catch();
    },
    handleCurrentChange(val){
      this.current=val;
      let departmentList;
      axios.post('http://' + store.baserUrl + '/hrms_regular/regular/departmentInfo')
          .then(response => {
            this.departmentList=response.data.data.list;
            departmentList=response.data.data.list;
            console.log(response.data);
          })
          .catch();
      let array=[];
      axios.post('http://' + store.baserUrl + '/hrms_employee/employee/hrDepartment',{'current':this.current,'size':this.size})
          .then(response => {
            this.list = response.data.data.list;
            this.total = Number(response.data.data.total);
            this.list.map(function (item) {
              //出勤比例天数
              let number=Math.ceil(item.dayTime/240*100);
              //日薪资
              let salarys=item.salary/20;
              //奖金prize
              let reward;
              if(number==100){
                //有奖金
                departmentList.forEach((p) => {
                  if(p.name==item.department){
                    reward=p.reward*item.salary;
                  }
                });

              }else{
                //没有奖金
                reward=0;
              }
              //总工资
              let totalWage=item.dayTime*salarys+reward;
              //实际应发工资
              let fact=0;
              if(totalWage<5000){
                fact=totalWage;
              }else if(8000>totalWage>5000){
                fact=totalWage-totalWage*3/100
              }else{
                fact=totalWage-totalWage*10/100
              }
              array.push({'id':item.id,'name':item.name,'number':number,'salary':salarys,'reward':reward,'totalWage':totalWage,'fact':fact,'department':item.department});
            })
            console.log(array)
            this.info=array;
            console.log(this.info)
          })
          .catch();
    },
    addRegular(){
    this.$router.push({path:'/backPage/addRegular'})
    },
    handleSizeChange(val){
      this.size=val;
      let departmentList;
      axios.post('http://' + store.baserUrl + '/hrms_regular/regular/departmentInfo')
          .then(response => {
            this.departmentList=response.data.data.list;
            departmentList=response.data.data.list;
            console.log(response.data);
          })
          .catch();
      let array=[];
      axios.post('http://' + store.baserUrl + '/hrms_employee/employee/hrDepartment',{'current':this.current,'size':this.size})
          .then(response => {
            this.list = response.data.data.list;
            this.total = Number(response.data.data.total);
            this.list.map(function (item) {
              //出勤比例天数
              let number=Math.ceil(item.dayTime/240*100);
              //日薪资
              let salarys=item.salary/20;
              //奖金prize
              let reward;
              if(number==100){
                //有奖金
                departmentList.forEach((p) => {
                  if(p.name==item.department){
                    reward=p.reward*item.salary;
                  }
                });

              }else{
                //没有奖金
                reward=0;
              }
              //总工资
              let totalWage=item.dayTime*salarys+reward;
              //实际应发工资
              let fact=0;
              if(totalWage<5000){
                fact=totalWage;
              }else if(8000>totalWage>5000){
                fact=totalWage-totalWage*3/100
              }else{
                fact=totalWage-totalWage*10/100
              }
              array.push({'id':item.id,'name':item.name,'number':number,'salary':salarys,'reward':reward,'totalWage':totalWage,'fact':fact,'department':item.department});
            })
            console.log(array)
            this.info=array;
            console.log(this.info)
          })
          .catch();
    }
  }
}
</script>

<style scoped>

</style>