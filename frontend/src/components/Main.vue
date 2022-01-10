<template>
  <Nav></Nav>
</template>
<script>
import Nav from "@/components/Navigation";

export default {
  name: "Main",
  components: {Nav},
  props: {}
}</script>
<style>
.nav-header {
  align-items: stretch;
  display: flex;
  flex-direction: row;
  min-height: 64px;
}
</style>
