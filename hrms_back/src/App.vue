<template>
  <div id="app">
   <router-view></router-view>

  </div>
</template>

<script>
import store from './assets/Store'
import axios from 'axios'

export default {
  name: 'App',
  components: {

  },
  created(){
    this.$router.push({path:'/userIndex'})
  },
  methods:{
    test(){
      axios.get('http://'+store.baserUrl+'/hrms_advertise/advertiseinfo'+'/test')
          .then(response=>{
            this.$message(response.data.data.msg);
          })
          .catch();
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
