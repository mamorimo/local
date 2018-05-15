<template>
  <div class="form-horizontal">
    <div class="form-group">
      <div class="col-sm-3">
        <input type="text"
          class="form-control"
          :value="this.$store.state.form.params.username.value"
          @keyup.stop="update($event.target.value, 'form.params.username.value')" />
      </div>
    </div>
    <div>
      <div v-for="r in this.$store.state.input1" v-bind:key="r.id">
        <inputons :labelvalue="r.value" :value="r.key"></inputons>
      </div>
    </div>
    <button class="btn" @click="isModal = true">モーダル</button>
    <transition name="modal" v-if="isModal">
      <div class="overlay" @click="isModal = false">
        <div class="panel" @click.stop>
          <button class="btn" @click="isModal = false">閉じる</button>
        </div>
      </div>
    </transition>
    <button @click="toPage5">Go To Page5</button>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      isModal: false
    }
  },
  methods: {
    update: function(value, path){
      this.$store.dispatch('inputUpdate', {value, path})
    },
    toPage5: function() {
      this.$router.push("/page5")
    }
  },
  components: {
    'inputons': {
      template:
      "<div>" +
        "{{labelvalue}}<input type='text' class='form-control' @keyup.stop='update(labelvalue, $event.target.value)' />" +
      "</div>",
      props: ['labelvalue', 'value'],
      methods: {
        update: function(label, value) {
          this.$store.dispatch('inputUpdate2', {label, value})
        }
      }
    }
  },
  mounted: function() {
    this.$store.dispatch('getInput1')
  }
}
</script>

<style>
.overlay {
    background: rgba(0, 0, 0, .8);
    position: fixed;
    width: 100%;
    height: 100%;
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
    z-index: 900;
    overflow: hidden;
    display: flex;
    align-items: center;
    justify-content: center;

}

.panel {
    width: 600px;
    height: 400px;
    background: #fff;
    padding: 20px;

    h3 {
        margin-bottom: 10px;
    }
}

.scroll-box {
    height: 300px;
    overflow-y: auto;
    margin-bottom: 20px;
    border: solid 1px #bbb;
}
.item-list {
    list-style: none;
    padding: 0;

    &.is-border {
        border: solid 1px #bbb;
        margin-bottom: 20px;
    }

    li {
        border-top: solid 1px #bbb;
        margin-top: -1px;
        padding: 10px;
        display: flex;
        justify-content: space-between;

        &:first-child {
            border-top: none;
        }
    }
}
.btn {
    width: 100%;
    padding: 15px;
    border: none;
    background: #eee;
}
</style>