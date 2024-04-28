<template>
<div>
  <el-form ref="form" :model="form" style="margin-left: 250px;width: 50%" label-width="80px">
    <el-form-item label="姓名">
      <el-input v-model="form.name"></el-input>
    </el-form-item>
    <el-form-item label="意愿岗位" style="margin-top: -630px">
      <el-input v-model="form.position"></el-input>
    </el-form-item>
    <el-form-item label="意愿部门" style="margin-top: -570px">
      <el-input v-model="form.department"></el-input>
    </el-form-item>
    <el-form-item label="性别"  style="margin-top: -510px">
      <el-input v-model="form.sex"></el-input>
    </el-form-item>
    <el-form-item label="年龄"  style="margin-top: -450px">
      <el-input v-model="form.age"></el-input>
    </el-form-item>
    <el-form-item label="学历"  style="margin-top: -390px">
      <el-input v-model="form.eduBackground"></el-input>
    </el-form-item>
    <el-form-item label="工作经验" style="margin-top: -330px">
      <el-input v-model="form.experience"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" style="margin-top: -270px">
      <el-input v-model="form.email"></el-input>
    </el-form-item>
    <el-form-item label="毕业院校" style="margin-top: -210px">
      <el-input v-model="form.school"></el-input>
    </el-form-item>
    <el-form-item label="工作经历" style="margin-top: -150px">
      <el-input v-model="form.undergo"></el-input>
    </el-form-item>
    <el-form-item label="专业" style="margin-top: -100px">
      <el-input v-model="form.speciality"></el-input>
    </el-form-item>
    <el-form-item style="margin-top: -50px">
      <el-button type="primary" @click="Approve">approve</el-button>
      <el-button @click="elimination">elimination</el-button>
    </el-form-item>
  </el-form>
</div>
</template>

<script>
import axios from "axios";
import store from "@/assets/Store";

export default {
  name: "InterviewPage",
  created(){
    this.id=this.$route.params.id;
    axios.post('http://' + store.baserUrl + '/hrms_advertise/advertiseinfo/SingleInfo',{'id':this.id})
        .then(response => {
          this.form = response.data.data.personInfo;
          console.log(this.form)
        })
        .catch();
    console.log();
  },
  data(){
   return {
     form:{},
     id:''
   }
  },
  methods:{
    Approve(){
      axios.post('http://' + store.baserUrl + '/hrms_advertise/advertiseinfo/EmployeeInfo',{'id':this.form.id, 'department':this.form.department,'companyId':this.form.companyId,'name':this.form.name,'position':this.form.position, 'sex':this.form.sex, 'age':this.form.age,'eduBackground':this.form.eduBackground, 'experience':this.form.experience, 'email':this.form.email, 'school':this.form.school,'speciality':this.form.speciality,'undergo':this.form.undergo, 'state':this.form.state})
          .then(response => {
          let message=response.data.data.msg;
          console.log(response.data.data.msg)
          if(message=='添加失败,请稍后尝试') {
            this.$router.push({path: '/backPage/AdvertiseResult/1'});
          }else if(message=='工资获取失败'){
            this.$message.error('公司可能不存在该部门,请检查公司部门规则,否则是服务器繁忙请稍后重试');
          }else{
            this.$router.push({path:'/backPage/AdvertiseResult/2'});
          }
          })
          .catch();
    },
    elimination(){
      //淘汰应聘人员
      axios.post('http://' + store.baserUrl + '/hrms_advertise/advertiseinfo/AdvertiseRemove/'+this.form.id)
          .then(response => {
          let msg=response.data.data.msg;
          this.$message.success(msg);
          })
          .catch();
    }
  }
}
</script>

<style scoped>

</style>