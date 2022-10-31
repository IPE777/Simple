<template>
  <div id="app">
    <el-container style="height: 900px; border: 1px solid #eee">
      <el-aside width="200px"  style="background-color: rgb(238, 241, 246)">

        <el-menu router :default-openeds="['0','1']">
          <el-submenu v-for="(item,index) in $router.options.routes" :key="index" :index="index+''" v-if="item.show">
            <template slot="title"><i class ="el-icon-setting">{{item.name}}</i></template>
            <el-menu-item v-for="(item2,index2) in item.children" :key="index2" :index="item2.path"
                          :class="$route.path===item2.path?'is-active':''">{{item2.name}}</el-menu-item>
          </el-submenu>

        </el-menu>

      </el-aside>


      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>


  </div>
</template>

<style>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 100px;
}

.el-aside {
  color: #333;
}
</style>

<script>
export default {
  data() {
    const item = {
      date: '2022-9-1',
      name: '王宇杭',
      address: '杭州市'
    };
    return {
      tableData: Array(20).fill(item)
    }
  }
};
</script>
