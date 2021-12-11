<template>
  <div v-if="hostel != null">

    <Hostel :hostel="this.hostel" />
      <carousel :perPage="one" autoplay=autoplay>
      <slide v-for="url in hostel.imageUrl" :key="url">
        <img :src="url">
      </slide>
    </carousel>
    <div v-for="facility in hostel.facilities" :key="facility" class="facilities">
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
      one: 1,
      autoplay: true
    };
  },
  methods: {

    getIndividualHostel() {
      HostelRequest.getIndividualHostel(this.$route.params.hostelId)
        .then((response) => {
          console.log(response.data);
          this.hostel = response.data;
          for (let i = 0; i < this.hostel.facilities.length; i++) {
            this.hostel.facilities[i] = this.camelCaseToNormalCase(this.hostel.facilities[i]);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },

    camelCaseToNormalCase(text) {
      const result = text.replace(/([A-Z])/g, " $1");
      const finalResult = result.charAt(0).toUpperCase() + result.slice(1);
      return finalResult;
    }
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

p {
  padding: 5px;
}

.facilities {
  display: inline-block;
  color: red;
}
</style>
