<template>
  <div>
    <div>
    <el-col :span="5">
      <el-card style="font-weight: bold;background-color: #F56C6C;height: 90px" shadow="hover">
        <span style="color:#409EFF;font-size: 24px">{{name}}</span>
      </el-card>
    </el-col>

    </div>
    <div>
      <el-row>
        <el-button @click="addAdvertise"  type="primary">添加应聘人员</el-button>
        <el-button @click="deleteAdvertise" type="danger">删除应聘人员</el-button>
        <el-button @click="EditAdvertise" type="danger">编辑应聘人员</el-button>
      </el-row>
    </div>
    <div>
      <el-table
          :data="list"
          border
          style="width: 70%;margin-left: 240px;margin-top: -500px">
        <el-table-column
            fixed
            prop="name"
            label="名称"
            width="150">
        </el-table-column>
        <el-table-column
            prop="position"
            label="岗位"
            width="120">
        </el-table-column>
        <el-table-column
            prop="department"
            label="部门"
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
            width="120">
        </el-table-column>
        <el-table-column
            prop="experience"
            label="工作经验"
            width="120">
        </el-table-column>

        <el-table-column
            prop="email"
            label="邮箱号码"
            width="120">
        </el-table-column>


        <el-table-column
            prop="school"
            label="毕业院校"
            width="120">
        </el-table-column>

        <el-table-column
            prop="undergo"
            label="工作经历"
            width="150">
        </el-table-column>


        <el-table-column
            prop="speciality"
            label="专业"
            width="120">
        </el-table-column>

        <el-table-column
            fixed="right"
            label="操作"
            width="180">
          <template slot-scope="scope">
            <el-checkbox-group v-if="scope.row.state" v-model="ids">
              <el-checkbox :label="scope.row.id" v-show="scope.row.state==0" :key="scope.row.id" name="type">{{operate}}</el-checkbox>
            </el-checkbox-group>
            <el-link v-if="scope.row.state" type="primary">
            <router-link v-show="scope.row.state==1" :to="'/backPage/interview/'+scope.row.id">
              面试审批
            </router-link>
            </el-link>
          </template>
        </el-table-column>

      </el-table>
      <el-row>

        <el-button  @click="submitPlus" style="margin-right: -750px;margin-top: 20px" type="primary">提交面试人员</el-button>

        <el-button v-show="state=='false'" @click="submitDelete" style="margin-right: -550px;margin-top: 20px" type="primary">提交删除人员</el-button>
      </el-row>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import store from "@/assets/Store";
export default {
  name: "hrAdvertise",
  created() {
    axios.post('http://' + store.baserUrl + '/hrms_advertise/advertiseinfo/AdvertiseInfo')
        .then(response => {
          this.list = response.data.data.list;
          this.name = response.data.data.name;
          console.log(this.list)
        })
        .catch();
  },
  methods:{
    submitPlus(){
      axios.post('http://' + store.baserUrl + '/hrms_advertise/advertiseinfo/addInterview',{'array':this.ids})
          .then(response => {
            this.list = response.data.data.list;
            this.name = response.data.data.name;
          })
          .catch();
      this.ids=[];
    },
    EditAdvertise(){
      //编辑招聘人员
      this.$router.push({path:'editAdvertise'})
    },
    addAdvertise(){
      this.$router.push({path:'/backPage/addAdvertise'});
    },
    InterViewInfo(id){
      this.$router.push({path:'/backPage/interview/'+id});
    },
    deleteAdvertise(){
    if(this.operate=='添加面试'){
      this.operate='选择删除';
    }else{
      this.operate='添加面试';
    }
    if(this.state=='true'){
      this.state='false';
    }else{
      this.state='true';
    }
    this.ids=[];
    },
    submitDelete(){
      axios.post('http://' + store.baserUrl + '/hrms_advertise/advertiseinfo/deleteAdvertise',{'array':this.ids})
          .then(response => {
            if(response.data.data.msg=='删除成功'){
              this.$message.success('人员删除成功');
            }else{
              this.$message.error('人员删除失败');
            }
            this.list = response.data.data.list;
          })
          .catch();
    }
    },
    data(){
      return {
        name:'',
        list:[],
        array:[],
        ids:[],
        operate:'添加面试',
        state:'true'
      }
    }
  }
</script>

<style scoped>
/* el-divider 修改高度&虚线效果 */
.el-divider--horizontal{
  margin: 8px 0;
  background: 0 0;
  border-top: 1px dashed #e8eaec;
  background-color:#409EFF;
}
.el-divider--horizontal{

}
</style>