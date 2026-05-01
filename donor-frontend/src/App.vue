<template>
  <el-config-provider :locale="elementLocale">
    <router-view />
  </el-config-provider>
</template>

<script setup>
import { computed } from 'vue'
import i18n from '@/locales'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import en from 'element-plus/es/locale/lang/en'
import ru from 'element-plus/es/locale/lang/ru'

const localeMap = { zh: zhCn, en, ru }

const elementLocale = computed(() => localeMap[i18n.global.locale.value] || zhCn)
</script>
