<template>
<div>
  <div>
  <el-select @change="EditInfo" v-model="position" placeholder="请选择公司部门">
    <el-option
        v-for="item in options"
        :key="item"
        :label="item"
        :value="item">
    </el-option>
  </el-select>
  <el-radio v-model="radio" @change="Situations(1)" label="1">在职状态</el-radio>
  <el-radio v-model="radio" @change="Situations(2)" label="2">岗位阶段</el-radio>
  <el-select  @change="EditInfo" v-model="situation" placeholder="请先选择在职状态或者岗位阶段">
    <el-option
        v-for="condition in conditions"
        :key="condition"
        :label="condition"
        :value="condition">
    </el-option>
  </el-select>
  </div>
  <div style="padding-left: 350px;margin-top: 80px">
    <el-table
        :data="list"
        border
        style="width: 80%">
      <el-table-column
          fixed
          prop="name"
          label="姓名"
          width="150">
      </el-table-column>
      <el-table-column
          prop="salary"
          label="薪资"
          width="120">
      </el-table-column>
      <el-table-column
          prop="department"
          label="公司部门"
          width="120">
      </el-table-column>
      <el-table-column
          prop="position"
          label="公司岗位"
          width="120">
      </el-table-column>
      <el-table-column
          prop="email"
          label="邮箱号码"
          width="300">
      </el-table-column>
      <el-table-column
          label="在职状态"
          width="120">
        <template slot-scope="scope">
         <span v-if="scope.row.state==0">全职</span>
          <span v-if="scope.row.state==1">兼职</span>
          <span v-if="scope.row.state==2">实习</span>
        </template>
      </el-table-column>
      <el-table-column
          label="岗位阶段"
          width="120">
        <template slot-scope="scope">
          <span v-if="scope.row.stage==0">试用期</span>
          <span v-if="scope.row.stage==1">待离职</span>
          <span v-if="scope.row.stage==2">正式</span>
        </template>
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          width="100">
        <template slot-scope="scope">
          <el-button type="text" @click="editEmployeeInfo(scope.row.id)" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</div>
</template>
<script>
import axios from "axios";
import store from "@/assets/Store";
export default {
  name: "EmployeeEdit",
  data(){
    return {
      value:'',
      options:[],
      radio:'',
      situation:'',
      position:'',
      list:[],
      conditions:[]
    }
  },
  created(){
    axios.post('http://' + store.baserUrl + '/hrms_employee/employee/PositionInfo')
        .then(response => {
          this.options=response.data.data.list;
          console.log(response.data);
        })
        .catch();
  },
  methods:{
    Situations(val){
      console.log(val);
      if(val===1){
       this.conditions=['全职','兼职','实习'];
      }else{
       this.conditions=['试用期','待离职','正式'];
      }
    },
    EditInfo(){
      axios.post('http://' + store.baserUrl + '/hrms_employee/employee/EditInfo',{'position':this.position,'situation':this.situation})
          .then(response => {
          this.list=response.data.data.list;
          console.log(response.data)
          })
          .catch();
    },
    editEmployeeInfo(val){
      this.$router.push({path:'EmployeeEditInfo/'+val});
    }
  }

}
</script>

<style scoped>
body{
  background-color: #E6A23C;
}
</style>