<template>
  <div>

    <el-form :inline="true"   class="demo-form-inline">
      <el-form-item>
        <el-button type="primary" @click="add">添加用户</el-button>
      </el-form-item>
      <el-form-item  >
        <el-input v-model="name"  placeholder="姓名"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit(name)">查询</el-button>
      </el-form-item>
    </el-form>



    <el-table
      :data="tableData"
      border
      style="width: 100%">

      <el-table-column

        prop="id"
        label="编号"
        min-width="25%">
      </el-table-column>
      <el-table-column

        prop="name"
        label="姓名"
        min-width="33%">
      </el-table-column>
      <el-table-column
        prop="age"
        label="年龄"
        min-width="25%">
      </el-table-column>
      <el-table-column
        prop="sex"
        label="性别"
        min-width="25%">
        <template slot-scope="scope">{{ scope.row.sex === 1 ? '男' : '女' }}</template>
      </el-table-column>
      <el-table-column label="头像" prop="picture">

        <template width="90" slot-scope="scope">

          <img style="width:80px;height:80px;border:none;" :src="scope.row.image">

        </template>

      </el-table-column>




      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="edit(scope.row)" type="text" size="small">编辑</el-button>
          <el-button @click="deleteUser(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      layout="prev, pager, next"
      page-size="5"
      :total=total
      @current-change="page">
    </el-pagination>

  </div>
</template>


<script>
import axios from "axios";


export default {
  methods: {
    add(){
      this.$router.push({
        path: '/add'

      })
    },
    deleteUser(row){
      const _this = this
      axios.get('http://localhost:7777/user/deleteById/'+row.id).then(function (resp){
        _this.$alert('删除成功', '消息', {
          confirmButtonText: '确定',
          callback: action => {
            window.location.reload()
          }
        })
      })
    },
    edit(row) {
      this.$router.push({
        path: '/update',
        query:{
          id: row.id
        }
      })
    },
    onSubmit(name){
      const _this = this
      if (name !== "") {
        axios.get('http://localhost:7777/user/findByName?name=' + name).then(function (resp) {
          console.log(resp)
          _this.tableData = resp.data.data

        })
      }else {
        window.location.reload()
      }
    },
    page(currentPage){
      const _this = this
      axios.get('http://localhost:7777/user/getUser/'+currentPage+'/5').then(function (resp){

        _this.tableData = resp.data.data.records
        _this.total = resp.data.data.total


      })
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:7777/user/getUser/1/5').then(function (resp){
      _this.tableData = resp.data.data.records

      _this.total = resp.data.data.total

    })

  },

  data() {
    return {
      total: null,
      tableData: null,
      name: ''
    }
  }
}
</script>
