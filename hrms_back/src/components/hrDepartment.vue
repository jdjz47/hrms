<template>
  <div>
<!--    <div>-->
<!--    <el-col :span="3">-->
<!--      <el-card style="font-weight: bold;background-color: #E6A23C;" shadow="hover">-->
<!--        <span style="color:#409EFF">{{name}}</span>-->
<!--      </el-card>-->

<!--    </el-col>-->
<!--    </div>-->
<!--      <div>-->
<!--    <el-col :span="3">-->
<!--      <el-card style="font-weight: bold;background-color: #409EFF;" shadow="hover">-->
<!--        <span style="font-size:24px;color:#F56C6C">  在职   {{ total }}  人</span>-->
<!--      </el-card>-->
<!--    </el-col>-->

<!--    <el-col :span="2">-->
<!--      <el-card style="background-color: #409EFF" shadow="hover">-->
<!--        全职 <span style="font-weight: bold;font-size:24px">{{ full }} </span> 人-->
<!--      </el-card>-->
<!--    </el-col>-->

<!--    <el-col :span="2">-->
<!--      <el-card style="background-color: #409EFF" shadow="hover">-->
<!--        兼职 <span style="font-weight: bold;font-size:24px">{{ hold }} </span> 人-->
<!--      </el-card>-->
<!--    </el-col>-->

<!--    <el-col :span="2">-->
<!--      <el-card style="background-color: #409EFF" shadow="hover">-->
<!--        实习 <span style="font-weight: bold;font-size:24px">{{ practice }} </span> 人-->
<!--      </el-card>-->
<!--    </el-col>-->

<!--    <el-col :span="2">-->
<!--      <el-card style="background-color: #409EFF;" shadow="hover">-->
<!--        试用期 <span style="font-weight: bold;font-size:24px">{{ probation }} </span> 人-->
<!--      </el-card>-->
<!--    </el-col>-->

<!--    <el-col :span="2">-->
<!--      <el-card style="background-color: #409EFF" shadow="hover">-->
<!--        待离职 <span style="font-weight: bold;font-size:24px">{{ resignation }} </span> 人-->
<!--      </el-card>-->
<!--    </el-col>-->
<!--    <el-col :span="2">-->
<!--      <el-card style="opacity:1%"  shadow="hover">-->
<!--      </el-card>-->
<!--    </el-col>-->




<!--    <el-col :span="2">-->
<!--      <el-card style="background-color: #E6A23C" shadow="hover">-->
<!--        试用期 <span style="font-weight: bold;font-size:24px">{{ probation }} </span>-->
<!--      </el-card>-->
<!--    </el-col>-->

<!--    <el-col :span="2">-->
<!--      <el-card style="background-color: #E6A23C" shadow="hover">-->
<!--        待离职 <span style="font-weight: bold;font-size:24px">{{ resignation }} </span>-->
<!--      </el-card>-->
<!--    </el-col>-->
<!--    <el-col :span="2">-->
<!--      <el-card style="background-color: #E6A23C" shadow="hover">-->
<!--        正式 <span style="font-weight: bold;font-size:24px">{{ officially }} </span> 人-->
<!--      </el-card>-->
<!--    </el-col>-->
<!--      </div>-->
    <div>
          <el-col :span="3">
            <el-card style="font-weight: bold;background-color: #F56C6C;height: 90px" shadow="hover">
              <span style="color:#409EFF;font-size: 24px">{{name}}</span>
            </el-card>
          </el-col>
    </div>
    <div>
    <div style="float:left;padding-top: 20px;border: 1px solid #409EFF;margin-left: 10px;height: 70px;width: 40%">
      <label style="font-size:24px;color:#409EFF">  在职  <label style="font-size: 40px;margin-top: -4px"> {{ totals }}</label>  人</label>
      <el-divider direction="vertical"></el-divider>
      全职 <label style="font-weight: bold;font-size:24px">{{ full }} </label> 人
      <el-divider direction="vertical"></el-divider>
      兼职 <span style="font-weight: bold;font-size:24px">{{ hold }} </span> 人
      <el-divider direction="vertical"></el-divider>
      实习 <label style="font-weight: bold;font-size:24px">{{ practice }} </label> 人
      </div>
    <div style="float:right;padding-top: 30px;border: 1px solid #409EFF;height: 60px;margin-right: 195px;width: 20%">
    <el-divider direction="vertical"></el-divider>
      试用期 <span style="font-weight: bold;font-size:24px">{{ probation }} </span>
      <el-divider direction="vertical"></el-divider>
      待离职 <span style="font-weight: bold;font-size:24px">{{ resignation }} </span>
      <el-divider direction="vertical"></el-divider>
      正式 <span style="font-weight: bold;font-size:24px">{{ officially }} </span> 人
    </div>
      <div></div>
      <div style="opacity: 0.5%">测试中</div>
      <div style="opacity: 0.5%">测试中</div>
      <div>
        <el-table
            :data="list"
            border
            style="width: 80%">
<!--          <el-table-column-->
<!--              fixed-->
<!--              prop="date"-->
<!--              label="日期"-->
<!--              width="150">-->
<!--          </el-table-column>-->
          <el-table-column
              prop="name"
              label="姓名"
              width="150">
          </el-table-column>
          <el-table-column
              prop="salary"
              label="员工薪资"
              width="150">
          </el-table-column>
          <el-table-column
              prop="department"
              label="公司部门"
              width="150">
          </el-table-column>
          <el-table-column
              prop="position"
              label="公司岗位"
              width="150">
          </el-table-column>
<!--          <el-table-column-->
<!--              v-show="state==0"-->
<!--              prop="state1"-->
<!--              label="在职状态"-->
<!--              width="120">-->
<!--          </el-table-column>-->

<!--          <el-table-column-->
<!--              v-show="state==1"-->
<!--              prop="state2"-->
<!--              label="在职状态"-->
<!--              width="120">-->
<!--          </el-table-column>-->

          <el-table-column
              label="在职状态"
              width="150">
            <template slot-scope="scope">
              <span v-if="scope.row.state==0">全职</span>
              <span v-if="scope.row.state==1">兼职</span>
              <span v-if="scope.row.state==2">实习</span>
            </template>
          </el-table-column>

          <el-table-column
              label="岗位阶段"
              width="150">
            <template slot-scope="scope">
              <span v-if="scope.row.stage==0">试用期</span>
              <span v-if="scope.row.stage==1">待离职</span>
              <span v-if="scope.row.stage==2">正式</span>
            </template>
          </el-table-column>

          <el-table-column
              prop="email"
              label="邮箱号码"
              width="180">
          </el-table-column>
        </el-table>
        <div class="block">
          <span class="demonstration">完整功能</span>
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="current"
              :page-sizes="[1, 3, 5, 7]"
              :page-size="size"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>
        </div>
      </div>
      <div>
      </div>
    </div>

  </div>
</template>

<script>
import axios from 'axios'
import store from "@/assets/Store";

export default {
  name: "hrDepartment",
  data() {
    return {
      full: '',
      hold: '',
      name: '',
      officially: '',
      practice: '',
      probation: '',
      resignation: '',
      list: [],
      total:0,
      current:0,
      size:10
    }
  },
  methods:{
    handleClick(row) {
      console.log(row);
    },
    handleSizeChange(val) {
    this.size=val;
    //返回修改页面大小的数据
      axios.post('http://' + store.baserUrl + '/hrms_employee/employee/hrDepartment',{'current':this.current,'size':this.size})
          .then(response => {
            this.list = response.data.data.list;
            this.total=Number(response.data.data.total);
            console.log(this.list);
          })
          .catch();
    },
    handleCurrentChange(val) {
    this.current=val;
    //TODO:修改返回当前页数的数据
      axios.post('http://' + store.baserUrl + '/hrms_employee/employee/hrDepartment',{'current':this.current,'size':this.size})
          .then(response => {
            this.list =response.data.data.list;
            this.total=Number(response.data.data.total);
            console.log(this.list);
          })
          .catch();
    }
  },
  created() {
    console.log("测试")
    axios.post('http://' + store.baserUrl + '/hrms_employee/employee/IndexCache')
        .then(response => {
          this.full = response.data.data.full;
          this.hold = response.data.data.hold;
          this.practice = response.data.data.practice;
          this.name = response.data.data.name;
          this.officially = response.data.data.officially;
          this.probation = response.data.data.probation;
          this.resignation = response.data.data.resignation;
          this.list = response.data.data.list;
          this.total=Number(response.data.data.total);
          console.log(this.list);
          console.log(response.data)
        })
        .catch();
  },
  computed: {
    totals: function () {
      return Number(this.full) + Number(this.hold) + Number(this.practice);
    },
  }
}
</script>

<style scoped>
body {

  background-repeat: no-repeat;
  background-size: 100%;
  background-position: 0px -50px;
}
</style>