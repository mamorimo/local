<template>
  <div>
    <div>
      {{this.$store.state.form.params.username.value}}
    </div>
    <div>
      {{this.$store.state.form.params.ininin}}
    </div>
    <button @click="back">Back</button>
  </div>
</template>

<script>
export default {
  methods: {
    back() {
      this.$router.go(-1)
    }
  }
}
</script>

<style>
</style>