<template>
  <carousel
    :perPage="one"
    autoplay="autoplay"
    paginationActiveColor="orange"
    navigationEnabled="true"
    class="mx-8"
  >
    <slide v-for="url in this.imageUrls" :key="url">
      <img :src="url" />
    </slide>
  </carousel>
</template>

<script>
import { Carousel, Slide } from "vue-carousel";

export default {
  name: "ImageSlideShow",
  props: ["imageUrls"],
  components: {
    Carousel,
    Slide,
  },
  data() {
    return {
      one: 1,
      autoplay: true,
    };
  },
};
</script>

<style scoped>
img {
  height: 300px;
}
</style>
