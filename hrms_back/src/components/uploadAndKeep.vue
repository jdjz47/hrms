<template>
<div>
  <el-upload
      class="upload-demo"
      drag
      accept=".xlsx, .xls"
      auto-upload="true"
      :action="'http://localhost:8086/hrms_employee/employee/upload/'+id"
      multiple>
    <i class="el-icon-upload"></i>
    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
    <div class="el-upload__tip" slot="tip">不超过500kb</div>
  </el-upload>
  <div>
    <div class="block">
      <span class="demonstration">默认</span>

    </div>
    <div style="padding-bottom: -150px">
      <el-date-picker
          format="yyyy-mm-dd"
          value-format="yyyy-MM-dd"
          v-model="value"
          type="date"
          placeholder="选择日期">
      </el-date-picker>
      <el-button @click="test" type="primary" style="margin-left: 16px;margin-top: 150px">
        下载文件列表
      </el-button>
      <el-drawer
          title="我是标题"
          :visible.sync="drawer"
          :direction="direction"
          :before-close="handleClose">
        <div v-for="(f,index) in list" v-bind:key="index">
          <h2>{{f.name}}</h2>
          <el-button @click="download(f.url)" type="primary" round>下载文件</el-button>
        </div>
      </el-drawer>
    </div>
  </div>
</div>
</template>

<script>
import axios from "axios";
import store from "@/assets/Store";

export default {
  name: "uploadAndKeep",
  created() {
    this.id=this.$route.params.id;
  },
  data() {
    return {
      id: '',
      value: '',
      direction: 'rtl',
      list:[],
      drawer:false
    }
  },
  methods: {
  test(){
    axios.post('http://'+store.baserUrl+'/hrms_uploadAnddown/upload/getList',{'dateTime':this.value})
        .then(response=>{
        this.list=response.data.data.list;
        console.log(response.data)
        this.drawer=true
        })
        .catch();
  },
    handleClose() {
    this.drawer=false;
  },
    download(val){
      window.location.assign(val)
    }
  }
}
</script>

<style scoped>

</style>