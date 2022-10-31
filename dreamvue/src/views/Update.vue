<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

    <el-form-item label="name" prop="name">
      <el-input v-model="ruleForm.name" style="width: 200px"></el-input>
    </el-form-item>
    <el-form-item label="age" prop="age">
      <el-input v-model="ruleForm.age" style="width: 200px"></el-input>
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
      <el-button type="primary" @click="submitForm('ruleForm')">修改</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>

    </el-form-item>

  </el-form>
</template>



<script>
import axios from "axios";

export default {
  name: "Update",
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
          { required: true, message: '请输入名称', trigger: 'blur' },
          { min: 2, max: 6, message: '长度在 2 到 6 个字符', trigger: 'blur' }
        ],
        age: [
          { required: true, message: '输入年龄', trigger: 'change' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this=this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.put('http://localhost:7777/user/updateById',this.ruleForm).then(function (resp){
            if(resp.data.data === 'success'){
              _this.$alert('修改成功', '消息', {
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
  },
  created() {
    const _this = this
    axios.get('http://localhost:7777/user/findById/'+this.$route.query.id).then(function (resp){
      _this.ruleForm = resp.data.data

    })
  }
}
</script>
