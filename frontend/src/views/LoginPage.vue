<template>
  <div class="login main">
    <AuthForm></AuthForm>
  </div>
</template>
<script>
import AuthForm from "@/components/AuthForm";

export default {
  name: 'LoginPage',
  components: {
    AuthForm
  },
  props: {
    msg: String
  }
}
</script>
<style>
.login.main {
  background-image: url('~@/assets/login.jpg');
  background-origin: content-box;
  background-size: cover;
  min-height: 960px;
  min-width: 100%
}
</style>