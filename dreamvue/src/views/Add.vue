<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

    <el-form-item label="name" prop="name">
      <el-input v-model="ruleForm.name"  style="width: 250px"></el-input>
    </el-form-item>
    <el-form-item label="age" prop="age">
      <el-input v-model="ruleForm.age" style="width: 250px"></el-input>
    </el-form-item>
    <el-upload
      class="avatar-uploader"
      action="http://localhost:7777/new/upload"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload">
      <img v-if="ruleForm.image" :src="ruleForm.image" class="avatar">
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>

    <el-select v-model="ruleForm.sex" placeholder="请选择">
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
    </el-select>


    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>

    </el-form-item>

  </el-form>
</template>


<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
<script>
import axios from "axios";

export default {
  name: "Add",
  data() {
    return {
      ruleForm: {
        id: '',
        name: '',
        age: '',
        image: '',
        sex: ''

      },
      options: [{
        value: 1,
        label: '男'
      },{
        value: 2,
        label: '女'
      }],

      rules: {

        name: [
          { required: true, message: '请输入名称', trigger: 'change' }
        ],
        age: [
          {  required: true, message: '请输入年龄', trigger: 'change' }
        ],

      }
    };
  },
  methods: {

    submitForm(formName) {
      const _this=this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:7777/user/save',this.ruleForm).then(function (resp){
            if(resp.data.data === 'success'){
              console.log(resp)
              _this.$alert('添加成功', '消息', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/user')
                }
              })
            }


          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleAvatarSuccess(response, file) {
      console.log(file.name)
      console.log("图片路径=" + response.returnFileUrl)
      this.ruleForm.image = response.returnFileUrl

    },

    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }

      return isJPG && isLt2M;
    }
  }
}
</script>


