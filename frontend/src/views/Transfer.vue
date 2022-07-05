<template>
  <Nav></Nav>
  <TransferBlock v-bind="toUuid"></TransferBlock>
</template>
<script>
import Nav from "@/components/Navigation";
import {getToCardUuid} from "@/constants/constants";
import TransferBlock from "@/components/TransferBlock";


export default {
  name: 'Transfer',
  components: {Nav, TransferBlock},
  props: {},
  data: function () {
    return {
      toUuid: ""
    }
  },
  created() {
    {
      {
        this.uuid = this.$route.params
        if (this.$route && this.$route.params.uuid) {
          localStorage.setItem(getToCardUuid(), JSON.stringify(this.$route.params));
        }
        this.toUuid = JSON.parse(localStorage.getItem(getToCardUuid()));
      }
    }
  }
}
</script>
<style>

</style>