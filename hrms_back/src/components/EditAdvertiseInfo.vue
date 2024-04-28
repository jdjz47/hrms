<template>
<div>
  <el-form ref="form" :model="info" style="margin-left: 150px" label-width="80px">
    <el-form-item style="width: 850px;margin-left: 40px" label="姓名">
      <el-input v-model="info.name"></el-input>
    </el-form-item>

    <el-form-item style="margin-left: 40px;margin-top: -620px" label="岗位">
      <el-input v-model="info.position"></el-input>
    </el-form-item>

    <el-form-item style="margin-left: 40px;margin-top: -560px" label="性别">
      <el-input v-model="info.sex"></el-input>
    </el-form-item>

    <el-form-item style="margin-left: 40px;margin-top: -500px" label="年龄">
      <el-input v-model="info.age"></el-input>
    </el-form-item>

    <el-form-item style="margin-left: 40px;margin-top: -440px" label="学历">
      <el-input v-model="info.eduBackground"></el-input>
    </el-form-item>

    <el-form-item style="margin-left: 40px;margin-top: -380px" label="工作经验">
      <el-input v-model="info.experience"></el-input>
    </el-form-item>


    <el-form-item style="margin-left: 40px;margin-top: -320px" label="邮箱号码">
      <el-input type="textarea" v-model="info.email"></el-input>
    </el-form-item>
    <el-form-item style="margin-left: 40px;margin-top: -260px" label="毕业院校">
      <el-input type="textarea" v-model="info.school"></el-input>
    </el-form-item>

    <el-form-item style="margin-left: 40px;margin-top: -200px" label="工作经历">
      <el-input type="textarea" v-model="info.undergo"></el-input>
    </el-form-item>


    <el-form-item style="margin-left: 40px;margin-top: -140px"  label="专业">
      <el-input type="textarea" v-model="info.speciality"></el-input>
    </el-form-item>

    <el-form-item  style="margin-left: 40px;margin-top: -80px"  label="面试阶段">
      <el-radio-group v-model="info.state">
                <el-radio label="0">
                  候选人
                </el-radio>
                <el-radio label="1">
                  面试中
                </el-radio>
      </el-radio-group>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="onSubmit">立即创建</el-button>
      <el-button @click="back()">取消</el-button>
    </el-form-item>
  </el-form>
</div>
</template>

<script>
import axios from "axios";
import store from "@/assets/Store";

export default {
  name: "EditAdvertiseInfo",
  data(){
    return {
      name:'',
      info:{}
    }
  },
  methods:{
    back(){
      this.$router.push({path:'/backPage/editAdvertise'})
    },
    onSubmit(){
      axios.post('http://' + store.baserUrl + '/hrms_advertise/advertiseinfo/EditAdvertiseHandle',{'id':this.info.id, 'department':this.info.department,'companyId':this.info.companyId,'name':this.info.name,'position':this.info.position, 'sex':this.info.sex, 'age':this.info.age,'eduBackground':this.info.eduBackground, 'experience':this.info.experience, 'email':this.info.email, 'school':this.info.school,'speciality':this.info.speciality,'undergo':this.info.undergo, 'state':this.info.state})
          .then(response => {
            let msg = response.data.data.msg;
            console.log(response.data)
            if(msg=='更新成功'){
              this.$router.push({path:'/backPage/AdvertiseResult/4'})
            }
          })
          .catch();
    }
  },
  created() {
    axios.post('http://' + store.baserUrl + '/hrms_advertise/advertiseinfo/EditAdvertiseInfo/'+this.$route.params.id)
        .then(response=>{
        console.log(response.data)
        this.name=response.data.data.info.name;
        console.log(response.data.data.info.name)
        this.info=response.data.data.info;
        })
        .catch();
  }
}
</script>

<style scoped>

</style>