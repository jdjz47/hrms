<template>
<div>
  <el-form ref="form" :model="form" style="margin-left: 200px"  label-width="80px">

    <el-form-item  label="部门名称">
      <el-input v-model="form.name"></el-input>
    </el-form-item>

    <el-form-item style="margin-top: -620px" label="薪资标准">
      <el-input  v-model="form.salary"></el-input>
    </el-form-item>

    <el-form-item style="margin-top: -520px" label="奖金标准">
      <el-input v-model="form.reward"></el-input>
    </el-form-item>

    <el-form-item style="margin-top: -320px">
      <el-button   type="primary" @click="onSubmit">立即创建</el-button>
      <el-button @click="back">取消</el-button>
    </el-form-item>
  </el-form>
</div>
</template>

<script>
import axios from "axios";
import store from "@/assets/Store";

export default {
  name: "addRegular",
  data(){
    return {
      form:{
        name:'',
        salary:'',
        reward:''
      },
      id:''
    }
  },
  created() {
    this.id=this.$route.params.id;
    if(this.id!=null){
      axios.post('http://' + store.baserUrl + '/hrms_regular/regular/getInfo',{'id':this.id})
          .then(response => {
            console.log(response.data)
          this.form=response.data.data.info;
          })
          .catch();
    }
    },
  methods:{
    onSubmit(){
      if(this.id==null){
      axios.post('http://' + store.baserUrl + '/hrms_regular/regular/addRegular',{'name':this.form.name,'salary':this.form.salary,'reward':this.form.reward})
          .then(response => {
            if(response.data.data.msg=='添加成功'){
              this.$message.success("添加成功");
              this.form={}
            }
          })
          .catch();
      }else{
        //修改
        axios.post('http://' + store.baserUrl + '/hrms_regular/regular/updateRegular',{'name':this.form.name,'salary':this.form.salary,'reward':this.form.reward,'id':this.form.id})
            .then(response => {
              this.$message.success("修改成功")
              console.log(response.data.data.list)
            })
            .catch();
      }
      },
    back(){
      this.$router.push({path:'/backPage/hrWage'});
    }
  }
}
</script>

<style scoped>

</style>