<template>
  <div>

    <div class="demo-image">
      <div class="block" >
        <span class="demonstration">{{ fit }}</span>
        <el-image
          style="width: 100px; height: 100px"
          :src="image"
          ></el-image>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      image: 'https://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/09/26/20ed1a5a-c700-4fb3-8078-22ef103a26f7.jpg'
    }
  }
}
</script>
