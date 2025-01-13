<script lang="ts" setup>
import {defineProps} from 'vue';

// Definimos una interfaz para los props
interface ProductCardProps {
  id?: string;
  title: string;
  description: string;
  imageUrl: string;
}

// Asociamos los props con la interfaz
const props = defineProps<ProductCardProps>();
</script>

<template>
  <div class="card bg-base-100 w-96 shadow-xl">
    <figure>
      <img :src="props.imageUrl" :alt="props.title"/>
    </figure>
    <div class="card-body">
      <h2 class="card-title">{{ props.title }}</h2>
      <p>{{ props.description }}</p>
      <div class="card-actions justify-end">
        <button class="btn btn-primary">Buy Now</button>
      </div>
    </div>
  </div>
</template>