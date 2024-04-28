<template>
<div>
 <el-row>
   <el-col v-if="state=='true'" :sm="12" :lg="6">
     <el-result icon="success" title="成功提示" :subTitle="info">
       <template slot="extra">
         <el-button @click="HistoryBack" type="primary" size="medium">返回</el-button>
       </template>
     </el-result>
   </el-col>
   <el-col  v-if="state=='false'" :sm="12" :lg="6">
     <el-result icon="error" title="错误提示" subTitle="服务器异常,请稍后再试">
       <template slot="extra">
         <el-button @click="HistoryBack" type="primary" size="medium">返回</el-button>
       </template>
     </el-result>
   </el-col>
 </el-row>
</div>
</template>

<script>
export default {
  name: "AdvertiseResult",
  data(){
    return {
      state:'true',
      info:''
    }
  },
  created() {
    let id=this.$route.params.id;
    console.log(id)
    if(id=='2'){
      console.log("测试1")
    this.state='true'
    this.info='面试通过,已成为贵公司内部员工';
    }else if(id=='1'){
      console.log("测试2")
    this.state='false';
    }else if(id=='3'){
      console.log("测试3")
      this.state='true'
      this.info='添加成功';
    }else{
      console.log("测试3")
      this.state='true'
      this.info='更新成功';
    }
    },
  methods:{
    HistoryBack(){
      this.$router.push({path:'/backPage/hrAdvertise'});
    }
  }
}
</script>

<style scoped>

</style>