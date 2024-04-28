<template>
  <div>
  <el-col :span="3">
    <el-menu
        :router="true"
        style="width: 200px;height: 720px;margin-top: -60px;margin-left: -20px"
        default-active="this.$route.path"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">

      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>员工信息管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/backPage/EmployeeAdd">
            员工添加
            <router-link to="/backPage/EmployeeAdd"></router-link>
          </el-menu-item>
          <el-menu-item index="/backPage/EmployeeEdit">
            员工编辑
            <router-link to="/backPage/EmployeeEdit"></router-link>
          </el-menu-item>
        </el-menu-item-group>

<!--        <el-submenu index="1-4">-->
<!--          <template slot="title">选项4</template>-->
<!--          <el-menu-item index="1-4-1">选项1</el-menu-item>-->
<!--        </el-submenu>-->
      </el-submenu>
      <el-menu-item index="/backPage/hrDepartment">
        <i class="el-icon-menu"></i>
        <span slot="title">人事管理</span>
        <router-link to="/backPage/hrDepartment"></router-link>
      </el-menu-item>

      <el-menu-item index="/backPage/hrAdvertise">
        <i class="el-icon-menu"></i>
        <span slot="title">招聘管理</span>
        <router-link to="/backPage/hrAdvertise"></router-link>
      </el-menu-item>
      <el-menu-item index="/backPage/hrWage">
        <i class="el-icon-menu"></i>
        <span slot="title">薪酬管理</span>
      </el-menu-item>

<!--      <el-menu-item index="3" disabled>-->
<!--        <i class="el-icon-document"></i>-->
<!--        <span slot="title">导航三</span>-->
<!--      </el-menu-item>-->
<!--      <el-menu-item index="4">-->
<!--        <i class="el-icon-setting"></i>-->
<!--        <span slot="title">导航四</span>-->
<!--      </el-menu-item>-->

    </el-menu>
  <button @click="test('https://guli-czp.oss-cn-hangzhou.aliyuncs.com/1/%E6%96%B0%E5%BB%BA%20Microsoft%20Excel%20%E5%B7%A5%E4%BD%9C%E8%A1%A8.xlsx')">
    点我
  </button>
  </el-col>
    <router-view style="top: -30px" name="innerView"></router-view>
  </div>
</template>

<script>
export default {
  name: "backPage",
  created() {

  },
  data() {
    return {

    };
  },
  methods:{
    test(val){
      //文件下载测试成功
      window.location.assign(val)
    }
  }
}
</script>

<style scoped>

</style>