<template>
  <div class="form-horizontal">
    <div class="form-group">
      <label class="col-sm-2 control-label">セレクトボックス１</label>
      <div class="col-sm-3">
        <select v-model="select1ed" class="form-control">
          <option v-for="r in selectes1" v-bind:value="r.value" v-bind:key="r.id">
            {{r.valueView}}
          </option>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">セレクトボックス２</label>
      <div class="col-sm-3">
        <select v-model="select2ed" class="form-control">
          <option v-for="r in selectes2" v-bind:value="r.value" v-bind:key="r.id">
            {{r.valueView}}
          </option>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">ラジオボタン</label>
      <label v-for="r in radios" v-bind:key="r.id" class="col-sm-1">
        <input type="radio" v-model="edit.radio1" v-bind:value="r.value" />
        {{r.label}}
      </label>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">テキストボックス</label>
      <div class="col-sm-3 input-group date" >
        <datepicker v-model="text1" v-bind:class="form-control"></datepicker>
      </div>
    </div>
    <div>
      <div class="form-group">
        <button type="button" id="tableSearch" class="btn btn-rimary" v-on:click="search">検索</button>
      </div>
      <div v-if="tableshow">
        <table id="searchTable" class="table table-striped table-bordered" style="width:100%">
          <thead>
            <tr>
            <th>col-1</th>
            <th>col-2</th>
            <th>col-3</th>
            <th>col-4</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="r in tableDatas.row" v-bind:key="r.id">
              <td>{{r.c1}}</td>
              <td>{{r.c2}}</td>
              <td>{{r.c3}}</td>
              <td><button type="button" v-on:click="toPage3(r)" class="btn btn-default btn-xs">Go To Page3</button></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="form-group">
      <button type="button" class="btn btn-rimary" v-on:click="layCalendar">カレンダー表示</button>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">カレンダー選択</label>
      {{edit.daySelect1ed}}
    </div>
    <div>
      <button type="button" class="btn btn-rimary" v-on:click="nextMonth">&gt;&gt;</button>
    </div>
    <div v-if="calendarShow">
    <div class="col-sm-4">
      <table>
        <tbody>
          <tr>
            <th>日</th>
            <th>月</th>
            <th>火</th>
            <th>水</th>
            <th>木</th>
            <th>金</th>
            <th>土</th>
          </tr>
          <tr v-for="r in m1" v-bind:key="r.id">
            <td v-for="ds in r.week" v-bind:key="ds.id">
              <div v-for="d in ds.daytimes" v-bind:key="d.id">
                <a class="btn btn-link active" v-on:click="timeSelected(d.id)">{{d.ts}}-{{d.te}}</a>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="col-sm-4">
      <table>
        <tbody>
          <tr>
            <th>日</th>
            <th>月</th>
            <th>火</th>
            <th>水</th>
            <th>木</th>
            <th>金</th>
            <th>土</th>
          </tr>
          <tr v-for="r in m2" v-bind:key="r.id">
            <td v-for="ds in r.week" v-bind:key="ds.id">
              <div v-for="d in ds.daytimes" v-bind:key="d.id">
                <a class="btn btn-link active" v-on:click="timeSelected(d.id)">{{d.ts}}-{{d.te}}</a>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">モーダルテキスト</label>
      {{modalButtonOn.text}}
    </div>
    <button type="button" id="sampleButton" class="btn btn-primary">モーダル・ダイアログ 呼び出し</button>
    <!-- モーダル・ダイアログ -->
    <div class="modal fade" id="sampleModal" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
            <h4 class="modal-title">タイトル</h4>
          </div>
          <div class="modal-body">
            テキスト１
            {{edit.text1}}
            {{modalButtonOn.text}}
            {{edit.modalInput}}
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">閉じる</button>
            <button type="button" class="btn btn-primary" v-on:click="modalButton1">ボタン</button>
          </div>
        </div>
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-2">
        <button type="submit" class="btn btn-default" v-on:click="toPage2">Go to page2</button>
      </div>
    </div>
  </div>
</template>

<script>
import Datepicker from 'vuejs-datepicker'

export default {
  components: {
    Datepicker
  },
  data: function(){
    return {
      selectes1: [],
      selectes2: [],
      radios: [],
      calendars: [],
      m1: [],
      m2: [],
      select1ed: '',
      select2ed: '',
      modalButtonOn: Object,
      tableDatas: null,
      text1: '',

      dataTable: null,

      edit: {
        select1edObj: {
          value: '',
          valueView: ''
        },
        select2edObj: {
          value: '',
          valueView: ''
        },
        radio1: '',
        text1: '',
        daySelect1ed: '',
        modalInput: ''
      }
    }
  },
  methods: {
    timeSelected: function(dayId) {
      this.edit.daySelect1ed = dayId
    },
    modalButton1: function() {
      axios.get('/vuetest/mockdata/modal1.json?ver=1')
        .then(response => (this.modalButtonOn = response.data))
    },
    search: async function() {
      await axios.get('/vuetest/mockdata/table1.json?ver=1')
        .then(response => (this.tableDatas = response.data))

      this.dataTable = $('#searchTable').DataTable({});
    },
    layCalendar: function() {
      axios.get('/vuetest/mockdata/calendar1.json')
        .then(response => (
          this.m1 = response.data.months1.weeks,
          this.m2 = response.data.months2.weeks))
    },
    nextMonth: function() {
      axios.get('/vuetest/mockdata/calendar2.json')
        .then(response => (
          this.m1 = response.data.months1.weeks,
          this.m2 = response.data.months2.weeks))
    },
    toPage2: function() {

      this.$store.dispatch('upDate', this.edit)

      this.$router.push("/page2")
    },
    toPage3: function(row) {

      this.$store.dispatch('searchTrance', row)

      this.$router.push("/page3")
    }
  },
  computed: {
    tableshow: function() {
      return this.tableDatas != null
    },
    calendarShow: function() {
      return this.m1.length > 0 && this.m2.length > 0
    }
  },
  watch: {
    select1ed: function(newValue, oldValue){
      this.edit.select1edObj = this.selectes1.filter(function(item){
        if (item.value == newValue) return true
      })[0]

      axios.get('/vuetest/mockdata/select2.json?ver=1')
        .then(response => (this.selectes2 = response.data.select))
    },
    select2ed: function(newValue, oldValue){
      this.edit.select2edObj = this.selectes2.filter(function(item){
        if (item.value == newValue) return true
      })[0]
    },
    modalButtonOn: function(newObject, oldObject){
        this.edit.modalInput = newObject.value
    }
  },
  mounted: function(){
      axios.get('/vuetest/mockdata/select1.json?ver=1')
        .then(response => (this.selectes1 = response.data.select))

      axios.get('/vuetest/mockdata/radio1.json')
        .then(response => (this.radios = response.data))
  }
}

$(function() {
  $('#sampleButton').click( function () {
    $('#sampleModal').modal();
  });

  $("#calendar-input").datetimepicker();
});
</script>

<style>
</style>
