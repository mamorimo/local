<template>
  <div class="form-horizontal">
    <div class="form-group">
      <label class="col-sm-2 control-label">セレクトボックス１</label>
      <div class="col-sm-2">
        <p class="form-control-static">{{this.$store.state.inputes.select1edObj.valueView}}</p>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">セレクトボックス２</label>
      <div class="col-sm-2">
        <p class="form-control-static">{{this.$store.state.inputes.select2edObj.valueView}}</p>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">ラジオボタン</label>
      <div class="col-sm-2">
        <p class="form-control-static">{{this.$store.state.inputes.radio1}}</p>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">テキストボックス</label>
      <div class="col-sm-2">
        <p class="form-control-static">{{this.$store.state.inputes.text1}}</p>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">カレンダー選択</label>
      <div class="col-sm-2">
        <p class="form-control-static">{{this.$store.state.inputes.daySelect1ed}}</p>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">モーダルダイアログ</label>
      <div class="col-sm-2">
        <p class="form-control-static">{{this.$store.state.inputes.modalInput}}</p>
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