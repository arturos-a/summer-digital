<template>
  <Nav></Nav>
  <CardBlock v-bind="uuid"></CardBlock>
</template>
<script>
import Nav from "@/components/Navigation";
import CardBlock from "@/components/CardBlock";
import {getCardUuid} from "@/constants/constants";


export default {
  name: 'Dashboard',
  components: {Nav, CardBlock},
  props: {},
  data: function () {
    return {
      uuid: ""
    }
  },
  created() {
    {
      {
        this.uuid = this.$route.params
        if (this.$route && this.$route.params.uuid) {
          localStorage.setItem(getCardUuid(), JSON.stringify(this.$route.params));
        }
        this.uuid = JSON.parse(localStorage.getItem(getCardUuid()));
      }
    }
  }
}
</script>
<style>

</style>