<template>
  <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item style="width: 800px;margin-left: 400px"  >
      <span slot="label">
          <span style="color: dodgerblue">公司名称</span>
        </span>
      <el-input type="text" v-model="name" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item style="width: 800px;margin-left: 400px"  prop="pass">
      <span slot="label">
          <span style="color: dodgerblue">密码</span>
        </span>
      <el-input type="password" v-model="password" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item style="width: 800px;margin-left: 400px" label="账号" >
       <span slot="label">
          <span style="color: royalblue">账号</span>
        </span>
      <el-input v-model.number="account"></el-input>
    </el-form-item>
    <el-form-item style="width: 800px;margin-left: 400px">
      <el-button type="primary" @click="submitForm">提交</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import axios from 'axios'
import store from '../assets/Store'
export default {
  name: "registerPage",
  methods:{
    submitForm(){
      axios.post('http://'+store.baserUrl+'/hrms_verify/companymanager/register',{'name':this.name,'account':this.account,'password':this.password})
          .then(response=>{
           let msg=response.data.data.msg;
            // eslint-disable-next-line no-empty
            if(msg=='注册成功'){
            this.$router.push({path:'userIndex'})
            }else{
              const h = this.$createElement;
              this.$notify({
                title: '注册失败',
                message: h('i', { style: 'color: teal'}, '您的该公司账号已经存在,或者您的账号密码格式错误')
              });
            }
          })
          .catch();
    }
  },
  data(){
    var checkAge = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('账号不能为空'));
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      name:'',
      password:'',
      account:'',
      ruleForm: {
        pass: '',
        checkPass: '',
        age: ''
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        age: [
          { validator: checkAge, trigger: 'blur' }
        ]
      },
    }
  }
}
</script>

<style scoped>

</style>