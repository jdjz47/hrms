<template>
  <div>

    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="姓名" style="width: 800px;margin-left: 200px">
        <el-input v-model="form.name"></el-input>
      </el-form-item>

      <el-form-item label="性别" style="width: 800px;margin-left: 200px;margin-top: -620px">
        <el-input v-model="form.sex"></el-input>
      </el-form-item>


      <el-form-item label="年龄" style="width: 800px;margin-left: 200px;margin-top: -560px">
        <el-input v-model="form.age"></el-input>
      </el-form-item>


      <el-form-item label="意愿岗位" style="width: 800px;margin-left: 200px;margin-top: -500px">
        <el-input v-model="form.position"></el-input>
      </el-form-item>


      <el-form-item label="学历" style="width: 800px;margin-left: 200px;margin-top: -440px">
        <el-input v-model="form.eduBackground"></el-input>
      </el-form-item>

      <el-form-item label="工作经验 " style="width: 800px;margin-left: 200px;margin-top: -380px">
        <el-input v-model="form.experience"></el-input>
      </el-form-item>

      <el-form-item label="邮箱号码" style="width: 800px;margin-left: 200px;margin-top: -320px">
        <el-input v-model="form.email"></el-input>
      </el-form-item>

      <el-form-item label="毕业学校" style="width: 800px;margin-left: 200px;margin-top: -260px">
        <el-input v-model="form.school"></el-input>
      </el-form-item>

      <el-form-item label="专业" style="width: 800px;margin-left: 200px;margin-top: -200px">
        <el-input v-model="form.speciality"></el-input>
      </el-form-item>


      <el-form-item label="工作经历" style="width: 800px;margin-left: 200px;margin-top: -140px">
        <el-input v-model="form.undergo"></el-input>
      </el-form-item>
      <label style="width: 200px;margin-left: -10px;margin-top: -100px">
    意愿部门:
      <el-select v-model="form.department"  placeholder="请选择" >
        <el-option
            v-for="item in list"
            :key="item.name"
            :label="item.name"
            :value="item.name">
        </el-option>
      </el-select>
</label>
      <el-button style="margin-left: 200px;margin-top: -20px" type="primary" @click="onSubmit">立即创建</el-button>
      <el-button @click="cancel">取消</el-button>

    </el-form>

  </div>
</template>

<script>
import axios from "axios";
import store from "@/assets/Store";

export default {
  name: "AddAdvertise",
  data() {
    return {
      form: {},
      list: [],

    }
  },
  created() {
    axios.post('http://' + store.baserUrl + '/hrms_regular/regular/departmentInfo')
        .then(response => {
          this.list = response.data.data.list;
          console.log(this.list)
        })
        .catch();
    console.log();
  },
  methods:{
    cancel(){
      this.$router.push({path:'/backPage/hrAdvertise'});
    },
    onSubmit(){
  axios.post('http://' + store.baserUrl + '/hrms_advertise/advertiseinfo/addAdvertise',{'id':this.form.id, 'department':this.form.department,'companyId':this.form.companyId,'name':this.form.name,'position':this.form.position, 'sex':this.form.sex, 'age':this.form.age,'eduBackground':this.form.eduBackground, 'experience':this.form.experience, 'email':this.form.email, 'school':this.form.school,'speciality':this.form.speciality,'undergo':this.form.undergo, 'state':this.form.state})
      .then(response => {
      let msg = response.data.data.msg;
      console.log(response.data)
      if(msg=='添加员工成功'){
      this.$router.push({path:'AdvertiseResult/3'})
      }
      })
      .catch();
  console.log();
    }
  }
}
</script>

<style scoped>

</style>