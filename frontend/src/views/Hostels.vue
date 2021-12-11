<template>
  <div>
    <h1>Hostel List</h1>
    <SearchBar @handle-search="handleSearch" searchHint="Search for hostels" />
    <div v-for="hostel in hostelList" :key="hostel.id">
      <div @click="goToViewMorePage(hostel.id)" id="hostel-click">
        <Hostel :hostel="hostel" />
      </div>
    </div>
    <Pagination :items="hostelList" :currentPage="currentPage" :pageSize="pageSize" v-on:page:update="updatePage"/>
  </div>
</template>

<script>
import HostelRequest from "../httpRequests/HostelRequest";
import Hostel from "../components/hostels/Hostel.vue";
import SearchBar from "../components/util/SearchBar";
import Pagination from "../components/util/Pagination.vue";

export default {
  name: "Hostels",
  components: {
    Hostel,
    SearchBar,
    Pagination
  },

  data() {
    return {
      hostelList: [],
      currentPage: 0,
      pageSize: 1,
    };
  },

  methods: {
    async getHostelList(startIndex, endIndex) {
      HostelRequest.getHostelList(startIndex, endIndex)
        .then((response) => {
          this.hostelList = response.data;
          console.log(this.hostelList);
          if (this.hostelList.length == 0 && this.currentPage > 0) {
            this.updatePage(this.currentPage - 1);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },

    goToViewMorePage(hostelId) {
      this.$router.push("/hostels/" + hostelId);
    },

    handleSearch(keyword) {
      HostelRequest.findHostels(keyword)
        .then((response) => {
          console.log("search result" + response.data);
          this.hostelList = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
 
    updatePage(pageNumber) {
      this.currentPage = pageNumber;
      this.updateVisibleHostels(this.currentPage * this.pageSize, (this.currentPage * this.pageSize) + this.pageSize);
    },
  },

  async mounted() { 
    await this.getHostelList(0, 1);
  },

  emits: ["handle-search"],
};
</script>

<style scope>
#hostel-click:hover {
  cursor: pointer;
}

.search-bar {
  float: right;
}
</style>
