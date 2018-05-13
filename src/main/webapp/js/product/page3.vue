<template>
  <div class="form-horizontal">
    <div class="form-group">
      <label class="col-sm-2 control-label">検索結果引継ぎ</label>
      <div class="col-sm-2">
        <p class="form-control-static">{{this.$store.state.tableRow}}</p>
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-2">
        <router-link to="/page1" class="btn btn-default">Go to page1</router-link>
      </div>
    </div>
  </div>
</template>

<script>
</script>

<style>
</style>