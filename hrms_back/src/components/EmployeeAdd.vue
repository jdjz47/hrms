<template>
  <div>
    <el-form :model="ruleForm" ref="ruleForm" style="margin-left: 200px" label-width="100px" class="demo-ruleForm">
      <el-form-item label="员工姓名" prop="name" style="margin-bottom:-600px ">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>

      <el-form-item label="员工薪资" style="margin-bottom:-550px" prop="name">
        <el-input value="前填写" v-model="ruleForm.salary"></el-input>
      </el-form-item>

      <el-form-item label="员工部门" style="margin-bottom:-500px" prop="name">
        <el-input v-model="ruleForm.department"></el-input>
      </el-form-item>

      <el-form-item label="公司岗位" style="margin-bottom:-450px" pr                                                                                                                                                                                                                                                                                                                                                                                                          op="name">
        <el-input v-model="ruleForm.position"></el-input>
      </el-form-item>

      <el-form-item label="邮箱号码" style="margin-bottom:-400px" prop="name">
        <el-input v-model="ruleForm.email"></el-input>
      </el-form-item>


      <el-form-item label="在职状态" style="margin-bottom:-350px" prop="state">
        <el-radio-group v-model="ruleForm.state">
          <el-radio label="全职"></el-radio>
          <el-radio label="兼职"></el-radio>
          <el-radio label="实习"></el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="岗位阶段" style="margin-bottom:-300px" prop="stage">
        <el-radio-group v-model="ruleForm.stage">
          <el-radio label="试用期"></el-radio>
          <el-radio label="待离职"></el-radio>
          <el-radio label="正式"></el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>

    <el-row>

      <el-button @click="EmployeeOperation" type="primary" round>提交数据</el-button>

    </el-row>
  </div>
</template>

<script>
import axios from "axios";
import store from "@/assets/Store";

export default {
  name: "EmployeeAdd",
  created() {
    this.id = this.$route.params.id;
    console.log("id",this.id)
    if (this.id !=''&&typeof (this.id)!='undefined') {
    console.log("测试中")
      //如果id不为空则是修改功能页面
      axios.post('http://' + store.baserUrl + '/hrms_employee/employee/EmployeeInfo', {'id': this.id})
          .then(response => {
            this.ruleForm = response.data.data.employeeInfo;
            let stateVal = response.data.data.employeeInfo.state;
            if (stateVal == '0') {
              this.ruleForm.state = '全职'
            }
             else if (stateVal == '1')
            {
              this.ruleForm.state = '兼职'
            }
          else
            {
              this.ruleForm.state = '实习'
            }
            let stageVal = response.data.data.employeeInfo.stage;
            if (stageVal == '0') {
              console.log("测试1")
              this.ruleForm.stage = '试用期'
            }
             else if (stageVal == '1')
            {
              console.log("测试2")
              this.ruleForm.stage = '待离职'
            }
          else
            {
              console.log("测试3")
              this.ruleForm.stage = '正式'
              console.log(stageVal,this.ruleForm.stage)
            }
          console.log(stageVal,this.ruleForm.stage)
            console.log(response.data);
          })
          .catch();
    }
  },
  data() {
    return {
      id: '',
      ruleForm: {
        name: '',
        department: '',
        salary: '',
        phone: '',
        state: '',
        stage: '',
        position: ''
      }
    }
  },
  methods: {
    EmployeeOperation() {
      if (this.id == ''||typeof (this.id)=='undefined') {
        console.log("测试成功")
        axios.post('http://' + store.baserUrl + '/hrms_employee/employee/addEmployee',
            {
              'name': this.ruleForm.name,
              'department': this.ruleForm.department,
              'salary': this.ruleForm.salary,
              'email': this.ruleForm.email,
              'stateTemp': this.ruleForm.state,
              'stageTemp': this.ruleForm.stage,
              'position': this.ruleForm.position,
              'operation': '1'
            })
            .then(response => {
              if (response.data.data.msg == '添加成功') {
                this.$message({
                  message: '添加员工成功',
                  type: 'success'
                });
                this.ruleForm = [];
              } else {
                this.$message.error('员工添加信息失败,请注意填写正确规范的信息');
              }
            })
            .catch();
      } else {
        axios.post('http://' + store.baserUrl + '/hrms_employee/employee/addEmployee',
            {
              'id':this.id,
              'name': this.ruleForm.name,
              'department': this.ruleForm.department,
              'salary': this.ruleForm.salary,
              'email': this.ruleForm.email,
              'stateTemp': this.ruleForm.state,
              'stageTemp': this.ruleForm.stage,
              'position': this.ruleForm.position,
              'operation': '2'
            })
            .then(response => {
              if (response.data.data.msg == '修改成功') {
                this.$message({
                  message: '员工信息修改成功',
                  type: 'success'
                });
                this.ruleForm = [];
              } else {
                this.$message.error('员工信息修改失败,请注意填写正确规范的信息');
              }
            })
            .catch();
      }
    }

  }
}
</script>

<style scoped>

</style>