<template>
  <div class="form-horizontal">
    <div class="form-group">
      <div class="col-sm-3">
        <datepicker v-model="text1" v-bind:inputClass="'form-control'"></datepicker>
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-3">
        <input type="text" class="form-control">
      </div>
    </div>
    <div class="form-group">
      <div class="form-group">
        <div class="col-sm-2">
          <button class="btn btn-primary" @click="show">Open Modal</button>
        </div>
      </div>
    </div>
    <modal name="hello-world" @before-close="beforeClose" @closed="closedOf">
      <div class="form-horizontal">
        <h1>hello, world!</h1>
        <div class="form-group">
          <div class="col-sm-2">
            <button class="btn btn-primary" v-on:click="hide">Close Modal</button>
          </div>
        </div>
      </div>
    </modal>
    <div class="form-group">
      <div class="form-group">
        <div class="col-sm-2">
          <button class="btn btn-primary" @click="toPage5">Go To Page5</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Datepicker from 'vuejs-datepicker'

export default {
  data: function() {
    return {
      isModal: false,
      text1: '',
      formControl: {}
    }
  },
  methods: {
    update: function(value, path){
      this.$store.dispatch('inputUpdate', {value, path})
    },
    toPage5: function() {
      this.$router.push("/page5")
    },
    show: function () {
      this.$modal.show('hello-world');
    },
    hide: function () {
      this.$modal.hide('hello-world');
    },
    beforeClose (event) {
      console.log(event)
    },
    closedOf (event) {
      console.log(event)
      this.$router.push("/page5")
    }
  },
  components: {
    Datepicker
  },
  mounted: function() {
    this.$store.dispatch('getInput1')
  }
}
</script>

<style>
body {
  font-family: "Helvetica Neue Light", Helvetica, sans-serif;
  padding: 1em 2em 2em;
}
input,
select {
  padding: 0.75em 0.5em;
  font-size: 100%;
  border: 1px solid #ccc;
  width: 100%;
}

select {
  height: 2.5em;
}

.example {
  background: #f2f2f2;
  border: 1px solid #ddd;
  padding: 0em 1em 1em;
  margin-bottom: 2em;
}

code,
pre {
  margin: 1em 0;
  padding: 1em;
  border: 1px solid #bbb;
  display: block;
  background: #ddd;
  border-radius: 3px;
}

.settings {
  margin: 2em 0;
  border-top: 1px solid #bbb;
  background: #eee;
}

h5 {
  font-size: 100%;
  padding: 0;
}

.form-group {
  margin-bottom: 1em;
}

.form-group label {
  font-size: 80%;
  display: block;
}
</style>