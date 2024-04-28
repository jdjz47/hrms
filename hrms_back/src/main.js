import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import jsCookie from "js-cookie";
import VueRouter from 'vue-router'
import index from './components/index'
import registerPage from "@/components/registerPage";
import backPage from "@/components/backPage";
import hrDepartment from "@/components/hrDepartment";
import hrAdvertise from "@/components/hrAdvertise";
import hrWage from "@/components/hrWage";
import hrVacation from "@/components/hrVacation";
import EmployeeAdd from "@/components/EmployeeAdd";
import EmployeeEdit from "@/components/EmployeeEdit";
import InterviewPage from "@/components/InterviewPage";
import AdvertiseResult from "@/components/AdvertiseResult";
import AddAdvertise from "@/components/AddAdvertise";
import EditAdvertise from "@/components/EditAdvertise";
import EditAdvertiseInfo from "@/components/EditAdvertiseInfo";
import UploadAndKeep from "@/components/uploadAndKeep";
import AddRegular from "@/components/addRegular";
Vue.use(VueRouter)
Vue.config.productionTip = false
Vue.use(ElementUI)
const router = new VueRouter({
    routes: [
        // 动态路径参数 以冒号开头
        {path: '/userIndex', component: index},
        {path: '/userRegister', component: registerPage},
        {path: '/backPage',
            component: backPage,
            children: [
                {
                name:'innerView',
                path: 'hrDepartment',
                components: {innerView:hrDepartment}
            },
                {
                    name:'innerView',
                    path: 'hrAdvertise',
                    components: {innerView:hrAdvertise}
                },
                {
                    name:'innerView',
                    path: 'hrWage',
                    components: {innerView:hrWage}
                },
                {
                    name:'innerView',
                    path: 'hrVacation',
                    components: {innerView:hrVacation}
                },
                {
                    name:'innerView',
                    path: 'employeeAdd',
                    components: {innerView:EmployeeAdd}
                },
                {
                    name:'innerView',
                    path: 'employeeEdit',
                    components: {innerView:EmployeeEdit}
                },
                {
                    name:'innerView',
                    path: 'EmployeeEditInfo/:id',
                    components: {innerView:EmployeeAdd}
                },
                {
                    name:'innerView',
                    path: 'interview/:id',
                    components: {innerView:InterviewPage}
                },
                {
                    name:'innerView',
                    path: 'AdvertiseResult/:id',
                    components: {innerView:AdvertiseResult}
                },
                {
                    name:'innerView',
                    path: 'addAdvertise',
                    components: {innerView:AddAdvertise}
                },
                {
                    name:'innerView',
                    path: 'editAdvertise',
                    components: {innerView:EditAdvertise}
                },
                {
                    name:'innerView',
                    path: 'editAdvertiseInfo/:id',
                    components: {innerView:EditAdvertiseInfo}
                },
                {
                    name:'uploadAndKeep',
                    path: 'uploadAndKeep/:id',
                    components: {innerView:UploadAndKeep}
                },
                {
                    name:'addRegular',
                    path: 'addRegular',
                    components: {innerView:AddRegular}
                },
                {
                    name:'addRegular',
                    path: 'updateRegular/:id',
                    components: {innerView:AddRegular}
                },
            ]

        }
    ]
})
new Vue({
    render: h => h(App),
    router
}).$mount('#app')

const basrUrl = 'localhost:8086'
export default {
    basrUrl
}
axios.interceptors.request.use(function (config) {
    config.headers['Token'] = jsCookie.get("token");
    console.log(jsCookie.get("token"))
    return config;
}, function (error) {
    return Promise.reject(error);
});
