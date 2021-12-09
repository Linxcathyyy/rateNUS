<template>
  <p class="hostel-description">{{ description }}</p>
</template>

<script>
export default {
  props: ["description"],
};
</script>

<style>
.hostel-description {
  border-left: 3px solid darkgray;
  padding: 10px 30px;
  text-align: left;
  line-height: 200%;
}
</style>
