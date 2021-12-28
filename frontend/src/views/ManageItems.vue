<template>
    <div class="my-comments">
      <ItemTable type="Hostel" />
      <!-- <ItemTable type="Stall" /> -->
  </div>
</template>

<script>
import ItemTable from "../components/commons/ItemTable.vue";

export default {
    name: "ManageItems",
    components: {
      ItemTable,
    },
};
</script>

<style scope>
</style>
