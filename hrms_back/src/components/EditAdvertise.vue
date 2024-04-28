<template>
<div>
  <el-button @click="drawer = true" type="primary" style="margin-left: 710px">
    点击筛选
  </el-button>
  <el-drawer
      title="条件筛选"
      :visible.sync="drawer"
      size="50%">



    <div>
      <el-button id="edu" @click="eduInfo">学历</el-button>
      <el-button id="department" @click="departmentInfo">部门</el-button>
      <el-button id="position" @click="positionInfo">岗位</el-button>
      <el-drawer
          title="请在以下条件中进行选择"
          :append-to-body="true"
          :before-close="handleClose"
          :visible.sync="innerDrawer">
          <div v-for="(p,index) in list" v-bind:key="index">
            <el-button style="margin-left: 20px;margin-top: 20px" type="primary" plain @click="chooseCondition(p)">{{p}}</el-button>
          </div>
         </el-drawer>
      <br>
      <el-tag
          size="medium"
          style="margin-top: 50px"
          v-for="tag in tags"
          :key="tag.name"
          closable
          @close="handleClose(tag)"
          :type="tag.type">
        {{tag.name}}
      </el-tag>

      <el-button  @click="ConditionQuery" type="primary" round>条件查询</el-button>

    </div>
  </el-drawer>
  <el-table
      :data="conditionList"
      border
      style="width: 60%;margin-top: 80px;margin-left: 350px">
    <el-table-column
        fixed
        prop="name"
        label="姓名"
        width="150">
    </el-table-column>
    <el-table-column
        prop="position"
        label="岗位"
        width="120">
    </el-table-column>
    <el-table-column
        prop="sex"
        label="性别"
        width="120">
    </el-table-column>
    <el-table-column
        prop="age"
        label="年龄"
        width="120">
    </el-table-column>
    <el-table-column
        prop="eduBackground"
        label="学历"
        width="300">
    </el-table-column>
    <el-table-column
        prop="experience"
        label="工作年限"
        width="120">
    </el-table-column>
    <el-table-column
        prop="phone"
        label="手机号码"
        width="120">
    </el-table-column>
    <el-table-column
        prop="school"
        label="毕业院校"
        width="120">
    </el-table-column>

    <el-table-column
        prop="speciality"
        label="专业"
        width="120">
    </el-table-column>

    <el-table-column
        prop="undergo"
        label="工作经历"
        width="120">
    </el-table-column>

    <el-table-column

        width="120">
      <template slot-scope="scope">
        <lable v-if="scope.state=='0'">
      候选人
        </lable>
        <lable v-if="scope.state=='1'">
      面试中
        </lable>

      </template>

    </el-table-column>

    <el-table-column
        fixed="right"
        label=""
        width="100">
      <template slot-scope="scope">
        <el-button type="text" @click="EditInfo(scope.row.id)" size="small">编辑</el-button>
      </template>
    </el-table-column>
  </el-table>
  <div class="block">
    <span class="demonstration">完整功能</span>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="current"
        :page-sizes="[1,5,10,20]"
        :page-size="size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
  </div>
</div>
</template>

<script>
import axios from "axios";
import store from "@/assets/Store";

export default {
  name: "EditAdvertise",
  data(){
    return {
      departments:[],
      department:'',
      eduBackgrounds:[],
      eduBackground:'',
      positions:[],
      position:'',
      drawer: false,
      innerDrawer: false,
      flag:'',
      list:[],
      tags: [],
      size:4,
      current:0,
      total:0,
      conditionList:[]
    }
  },
  methods:{
    eduInfo(){
      this.innerDrawer = true
      this.flag='1';
      axios.post('http://' + store.baserUrl + '/hrms_advertise/advertiseinfo/condition/1')
          .then(response => {
        this.list=response.data.data.list;
        console.log(response.data.data.list)
          })
          .catch();
    },
    handleCurrentChange(val){
      this.current=val;
      //学历
      let eduBackground;
      //部门
      let department;
      //岗位
      let position;
      this.tags.map((item) => {
        if (item.id == "1") {
          eduBackground=item.name;
        }else if(item.id=='2'){
          department=item.name
        }else{
          position=item.name;
        }
      });
      axios.post('http://' + store.baserUrl + '/hrms_advertise/advertiseinfo/ConditionQuery',{'current':this.current,'size':this.size,'eduBackground':eduBackground,'department':department,'position':position})
          .then(response => {
            this.conditionList=response.data.data.list;
            this.total=response.data.data.total;
            console.log(response.data.data.list)
          })
          .catch();
    },
    EditInfo(id){
    console.log(id)
      this.$router.push({path:'editAdvertiseInfo/'+id})
    },
    handleSizeChange(val){
      console.log(val)
      this.size=val;
      //学历
      let eduBackground;
      //部门
      let department;
      //岗位
      let position;
      this.tags.map((item) => {
        if (item.id == "1") {
          eduBackground=item.name;
        }else if(item.id=='2'){
          department=item.name
        }else{
          position=item.name;
        }
      });
      axios.post('http://' + store.baserUrl + '/hrms_advertise/advertiseinfo/ConditionQuery',{'current':this.current,'size':this.size,'eduBackground':eduBackground,'department':department,'position':position})
          .then(response => {
            this.conditionList=response.data.data.list;
            this.total=response.data.data.total;
            console.log(response.data.data.list)
          })
          .catch();
    },
    ConditionQuery(){
      //学历
      let eduBackground;
      //部门
      let department;
      //岗位
      let position;
      this.tags.map((item) => {
        if (item.id == "1") {
          eduBackground=item.name;
        }else if(item.id=='2'){
          department=item.name
        }else{
          position=item.name;
        }
      });
      axios.post('http://' + store.baserUrl + '/hrms_advertise/advertiseinfo/ConditionQuery',{'current':this.current,'size':this.size,'eduBackground':eduBackground,'department':department,'position':position})
          .then(response => {
            this.conditionList=response.data.data.list;
            this.total=response.data.data.total;
            console.log(response.data.data.list)
          })
          .catch();
    },
    departmentInfo(){
      this.innerDrawer = true
      this.flag='2';
      axios.post('http://' + store.baserUrl + '/hrms_advertise/advertiseinfo/condition/2')
          .then(response => {
            this.list=response.data.data.list;
          })
          .catch();
    },
    chooseCondition(p){
      console.log(p)
      this.innerDrawer=false
      if(this.flag=='1'){
        this.tags.push({ name: p,id:'1' });
      document.getElementById("edu").disabled=true
      }else if(this.flag=='2'){
        document.getElementById("department").disabled=true
        this.tags.push({ name: p,id:'2'});
      }else if(this.flag=='3'){
        this.tags.push({ name: p,id:'3'});
        document.getElementById("position").disabled=true
      }
    },
    positionInfo(){
      this.innerDrawer = true
      this.flag='3';
      axios.post('http://' + store.baserUrl + '/hrms_advertise/advertiseinfo/condition/3')
          .then(response => {
            this.list=response.data.data.list;
          })
          .catch();
    },
    handleClose(tag){
      if(tag.id=='1'){
        document.getElementById("edu").disabled=false
        this.tags.map((item, index) => {
          if (item.id == "1") {
            this.tags.splice(index, 1);
          }
        });
      }else if(tag.id=='2'){
        document.getElementById("department").disabled=false
        this.tags.map((item, index) => {
          if (item.id == "2") {
            this.tags.splice(index, 1);
          }
        });
      }else{
        this.tags.map((item, index) => {
          if (item.id == "3") {
            this.tags.splice(index, 1);
          }
        });
        document.getElementById("position").disabled=false
      }
      this.innerDrawer=false;
    }
  },
  created(){
    this.ConditionQuery();
  }
}
</script>

<style scoped>

</style>