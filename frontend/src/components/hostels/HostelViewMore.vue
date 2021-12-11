<template>
  <div v-if="hostel != null">
    <Hostel :hostel="this.hostel" />
    <img :src="hostel.imageUrl" alt="hostel image">
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

export default {
  name: "HostelViewMore",
  components: { Hostel, Comments, CommentForm },
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

<style></style>
