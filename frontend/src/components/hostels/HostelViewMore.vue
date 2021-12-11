<template>
  <div v-if="hostel != null">

    <Hostel :hostel="this.hostel" />
      <carousel perPage=1 autoplay=true>
      <slide v-for="url in hostel.imageUrl" :key="url">
        <img :src="url">
      </slide>
    </carousel>
    <div v-for="facility in hostel.facilities" :key="facility">
        <p> {{ facility }} </p>
    </div>
    <CommentForm />
    <Comments />
  </div>
</template>

<script>
import HostelRequest from "../../httpRequests/HostelRequest";
import Hostel from "./Hostel.vue";
import Comments from "../Comments.vue";
import CommentForm from "../CommentForm.vue";
import { Carousel, Slide } from "vue-carousel";

export default {
  name: "HostelViewMore",
  components: { 
    Hostel,
    Comments, 
    CommentForm, 
    Carousel,
    Slide
    },
  data() {
    return {
      hostel: null,
      commentList: [],
    };
  },
  methods: {
    getIndividualHostel() {
      HostelRequest.getIndividualHostel(this.$route.params.hostelId)
        .then((response) => {
          console.log(response.data);
          this.hostel = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
    this.getIndividualHostel();
  },
};
</script>

<style scoped>
img {

  height: 300px;
}
</style>
