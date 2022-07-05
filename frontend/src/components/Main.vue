<template>
  <Nav></Nav>
  <div class="dashboard-body">
    <Products></Products>
  </div>
</template>
<script>
import Nav from "@/components/Navigation";
import Products from "@/components/Products";

export default {
  name: "Main",
  components: {Products, Nav},
  props: {}
}</script>
<style>
.nav-header {
  align-items: stretch;
  display: flex;
  flex-direction: row;
  min-height: 64px;
}

.dashboard-body {
  background-color: #fbfbfb;
}
</style>
