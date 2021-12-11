<template>
  <div v-if="totalPages() > 0" class="pagination-wrapper">
      <span v-if="showPreviousLink()" class="pagination-btn" v-on:click="updatePage(currentPage - 1)"> h </span>
        {{ currentPage + 1}} of {{ totalPages()}}
      <span v-if="showNextLink()" class="pagination-btn" v-on:click="updatePage(currentPage - 1)"> > </span>
  </div>
</template>

<script>
export default {
  name: "Pagination",
  props: {
      items: [],
      currentPage: Number,
      pageSize: Number,
  },
  methods: {
      totalPages() {
          return Math.ceil(this.items.length / this.pageSize);
      },
      updatePage(pageNumber) {
          this.$emit('page:update', pageNumber);
      },
      showPreviousLink() {
          return this.currentPage == 0 ? false : true;
      },
      showNextLink() {
          return this.currentPage == (this.totalPages() - 1) ? false : true;
      }
  },
};
</script>

<style scoped>
.pagination-btn {
    cursor: pointer;
}
</style>
