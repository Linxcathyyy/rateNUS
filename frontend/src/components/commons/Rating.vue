<template>
  <v-rating
    :value="scoreTo2dp"
    color="amber"
    dense
    half-increments
    readonly
    size="14"
  ></v-rating>
</template>

<script>
export default {
  props: ["score"],
  data() {
    return {
      scoreTo2dp: Number(this.score.toPrecision(2))
    }
  }
  };
</script>

<style scope>
.rating {
  color: rgb(32, 34, 58);
}
</style>
