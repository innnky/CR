<template>
  <div class="chart"></div>
</template>

<script>

export default {
  name: "MyEchart",
  watch: {
    seriesData: {
      handler: function () {

      },
      deep: true
    }
  },
  data() {
    return {
      seriesData: []
    }
  }
}
</script>

<style scoped>

</style>
