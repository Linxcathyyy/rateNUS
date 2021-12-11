<template>
  <p class="rating">Rating: {{ scoreTo2dp }}/5.0 </p>
</template>

<script>
export default {
  props: ["score"],
  data() {
    return {
      scoreTo2dp: this.score.toPrecision(2)
    }
  }
  };
</script>

<style scope>
.rating {
  color: rgb(32, 34, 58);
}
</style>
