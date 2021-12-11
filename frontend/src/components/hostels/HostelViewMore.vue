<template>
  <div v-if="hostel != null">
    <Hostel :hostel="this.hostel" />
    <div class="facilities-and-image">
      <div class="facilities">
        <div>Facilities available:</div>
        <div v-for="facility in hostel.facilities" :key="facility">
          <li>{{ facility }}</li>
        </div>
      </div>
      <img :src="hostel.imageUrl" alt="hostel image" class="hostel-image" />
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

<style scoped>
.hostel-image {
  width: 100%;
  max-height: 200px;
  object-fit: cover;
}

.facilities-and-image {
  display: grid;
  grid-template-columns: 1fr 1fr;
  margin: 20px 30px;
}

.facilities > div {
  text-align: left;
  margin: 20px 30px;
  font-size: 20px;
}
</style>
