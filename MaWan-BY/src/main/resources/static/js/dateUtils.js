/**
 * 统一的日期格式化工具
 * 用于在整个应用中统一日期和时间的显示格式
 */

(function() {
    'use strict';

    /**
     * 获取当前语言环境
     */
    function getLocale() {
        const lang = window.LanguageManager?.getCurrent?.() || localStorage.getItem('app_lang') || 'zh';
        if (lang === 'en') return 'en-US';
        if (lang === 'ru') return 'ru-RU';
        return 'zh-CN';
    }

    /**
     * 格式化日期（仅日期，不包含时间）
     * 格式：YYYY-MM-DD（中文）或 MM/DD/YYYY（英文）
     * 
     * @param {string|Date} value - 日期值（ISO字符串或Date对象）
     * @returns {string} 格式化后的日期字符串
     */
    function formatDate(value) {
        if (!value) return window.t ? window.t('textUnknown') : '-';
        try {
            const date = value instanceof Date ? value : new Date(value);
            if (isNaN(date.getTime())) return window.t ? window.t('textUnknown') : '-';
            
            const locale = getLocale();
            const options = { 
                year: 'numeric', 
                month: '2-digit', 
                day: '2-digit',
                timeZone: 'Asia/Shanghai'
            };
            return date.toLocaleDateString(locale, options);
        } catch (e) {
            console.error('Date formatting error:', e);
            return window.t ? window.t('textUnknown') : '-';
        }
    }

    /**
     * 格式化日期时间（包含日期和时间）
     * 格式：YYYY-MM-DD HH:mm:ss（中文）或 MM/DD/YYYY HH:mm:ss（英文）
     * 
     * @param {string|Date} value - 日期时间值（ISO字符串或Date对象）
     * @returns {string} 格式化后的日期时间字符串
     */
    function formatDateTime(value) {
        if (!value) return window.t ? window.t('textUnknown') : '-';
        try {
            const date = value instanceof Date ? value : new Date(value);
            if (isNaN(date.getTime())) return window.t ? window.t('textUnknown') : '-';
            
            const locale = getLocale();
            const options = {
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
                hour: '2-digit',
                minute: '2-digit',
                second: '2-digit',
                hour12: false,
                timeZone: 'Asia/Shanghai'
            };
            return date.toLocaleString(locale, options);
        } catch (e) {
            console.error('DateTime formatting error:', e);
            return window.t ? window.t('textUnknown') : '-';
        }
    }

    /**
     * 格式化日期时间（简短格式，不包含秒）
     * 格式：YYYY-MM-DD HH:mm（中文）或 MM/DD/YYYY HH:mm（英文）
     * 
     * @param {string|Date} value - 日期时间值（ISO字符串或Date对象）
     * @returns {string} 格式化后的日期时间字符串
     */
    function formatDateTimeShort(value) {
        if (!value) return window.t ? window.t('textUnknown') : '-';
        try {
            const date = value instanceof Date ? value : new Date(value);
            if (isNaN(date.getTime())) return window.t ? window.t('textUnknown') : '-';
            
            const locale = getLocale();
            const options = {
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
                hour: '2-digit',
                minute: '2-digit',
                hour12: false,
                timeZone: 'Asia/Shanghai'
            };
            return date.toLocaleString(locale, options);
        } catch (e) {
            console.error('DateTime formatting error:', e);
            return window.t ? window.t('textUnknown') : '-';
        }
    }

    /**
     * 将日期时间格式转换为API所需的ISO格式
     * 输入：datetime-local输入框的值（YYYY-MM-DDTHH:mm）
     * 输出：ISO 8601格式（YYYY-MM-DDTHH:mm:ss）
     * 
     * @param {string} value - 日期时间字符串
     * @returns {string|null} ISO格式的日期时间字符串，如果无效则返回null
     */
    function formatDateTimeForApi(value) {
        if (!value) return null;
        let formatted = value.trim();
        if (!formatted) return null;
        
        // 替换斜杠为横线
        formatted = formatted.replace(/\//g, '-');
        
        // 如果没有T但有空格，将空格替换为T
        if (!formatted.includes('T') && formatted.includes(' ')) {
            formatted = formatted.replace(' ', 'T');
        }
        
        // 如果是datetime-local格式（16字符），添加秒
        if (formatted.includes('T') && formatted.length === 16) {
            formatted += ':00';
        }
        
        // 验证ISO格式
        const isoPattern = /^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}$/;
        return isoPattern.test(formatted) ? formatted : null;
    }

    /**
     * 格式化日期为"年/月/日"格式
     * @param {string} dateStr - YYYY-MM-DD格式的日期字符串
     * @returns {string} 格式化后的日期字符串，格式：年/月/日
     */
    function formatDateForDisplay(dateStr) {
        if (!dateStr) return '';
        const parts = dateStr.split('-');
        if (parts.length === 3) {
            return `${parts[0]}年${parseInt(parts[1])}月${parseInt(parts[2])}日`;
        }
        return dateStr;
    }

    /**
     * 格式化日期时间为"年/月/日 时:分"格式
     * @param {string} dateTimeStr - YYYY-MM-DDTHH:mm格式的日期时间字符串
     * @returns {string} 格式化后的日期时间字符串，格式：年/月/日 时:分
     */
    function formatDateTimeForDisplay(dateTimeStr) {
        if (!dateTimeStr) return '';
        const [datePart, timePart] = dateTimeStr.split('T');
        if (datePart && timePart) {
            const dateFormatted = formatDateForDisplay(datePart);
            const [hour, minute] = timePart.split(':');
            return `${dateFormatted} ${hour}:${minute}`;
        }
        return dateTimeStr;
    }

    /**
     * 初始化日期输入框的显示格式
     * 统一所有日期输入框的显示格式为"年/月/日"或"年/月/日 时:分"
     */
    function initDateInputs() {
        // 处理所有type="date"的输入框
        document.querySelectorAll('input[type="date"]:not(.date-formatted)').forEach(function(input) {
            // 标记为已处理，避免重复处理
            input.classList.add('date-formatted');
            
            // 设置语言属性为中文以影响显示格式
            input.setAttribute('lang', 'zh-CN');
            
            // 确保输入框的父元素是相对定位
            const parent = input.parentElement;
            if (getComputedStyle(parent).position === 'static') {
                parent.style.position = 'relative';
            }
            
            // 创建一个显示元素来显示格式化的日期
            const displaySpan = document.createElement('span');
            displaySpan.className = 'date-display-helper';
            displaySpan.style.cssText = 'position: absolute; left: 12px; top: 50%; transform: translateY(-50%); pointer-events: none; color: var(--text-dark, #1f2537); font-size: 0.9rem; z-index: 1; background: white; padding: 0 2px;';
            
            // 将显示元素插入到输入框后面
            input.parentNode.insertBefore(displaySpan, input.nextSibling);
            
            // 更新显示的函数
            function updateDisplay() {
                if (input.value) {
                    const formatted = formatDateForDisplay(input.value);
                    displaySpan.textContent = formatted;
                    // 隐藏输入框的原始文本显示
                    input.style.color = 'transparent';
                    input.style.caretColor = 'var(--primary, #5b7cfa)';
                    displaySpan.style.display = '';
                } else {
                    displaySpan.textContent = '';
                    displaySpan.style.display = 'none';
                    input.style.color = '';
                }
            }
            
            // 监听各种事件
            input.addEventListener('change', updateDisplay);
            input.addEventListener('input', updateDisplay);
            input.addEventListener('focus', function() {
                // 获得焦点时隐藏格式化显示，显示原始值以便编辑
                displaySpan.style.display = 'none';
                input.style.color = '';
            });
            input.addEventListener('blur', function() {
                // 失去焦点时显示格式化值
                setTimeout(function() {
                    updateDisplay();
                }, 100);
            });
            
            // 初始化显示
            updateDisplay();
        });
        
        // 处理所有type="datetime-local"的输入框
        document.querySelectorAll('input[type="datetime-local"]:not(.datetime-formatted)').forEach(function(input) {
            // 标记为已处理
            input.classList.add('datetime-formatted');
            
            // 设置语言属性为中文
            input.setAttribute('lang', 'zh-CN');
            
            // 确保输入框的父元素是相对定位
            const parent = input.parentElement;
            if (getComputedStyle(parent).position === 'static') {
                parent.style.position = 'relative';
            }
            
            // 创建一个显示元素来显示格式化的日期时间
            const displaySpan = document.createElement('span');
            displaySpan.className = 'datetime-display-helper';
            displaySpan.style.cssText = 'position: absolute; left: 12px; top: 50%; transform: translateY(-50%); pointer-events: none; color: var(--text-dark, #1f2537); font-size: 0.9rem; z-index: 1; background: white; padding: 0 2px;';
            
            // 将显示元素插入到输入框后面
            input.parentNode.insertBefore(displaySpan, input.nextSibling);
            
            // 更新显示的函数
            function updateDisplay() {
                if (input.value) {
                    const formatted = formatDateTimeForDisplay(input.value);
                    displaySpan.textContent = formatted;
                    // 隐藏输入框的原始文本显示
                    input.style.color = 'transparent';
                    input.style.caretColor = 'var(--primary, #5b7cfa)';
                    displaySpan.style.display = '';
                } else {
                    displaySpan.textContent = '';
                    displaySpan.style.display = 'none';
                    input.style.color = '';
                }
            }
            
            // 监听各种事件
            input.addEventListener('change', updateDisplay);
            input.addEventListener('input', updateDisplay);
            input.addEventListener('focus', function() {
                // 获得焦点时隐藏格式化显示
                displaySpan.style.display = 'none';
                input.style.color = '';
            });
            input.addEventListener('blur', function() {
                // 失去焦点时显示格式化值
                setTimeout(function() {
                    updateDisplay();
                }, 100);
            });
            
            // 初始化显示
            updateDisplay();
        });
    }

    // 导出到全局作用域
    window.DateUtils = {
        formatDate: formatDate,
        formatDateTime: formatDateTime,
        formatDateTimeShort: formatDateTimeShort,
        formatDateTimeForApi: formatDateTimeForApi,
        formatDateForDisplay: formatDateForDisplay,
        formatDateTimeForDisplay: formatDateTimeForDisplay,
        getLocale: getLocale,
        initDateInputs: initDateInputs
    };

    // 为了向后兼容，也导出为全局函数
    window.formatDate = formatDate;
    window.formatDateTime = formatDateTime;
    window.formatDateTimeDisplay = formatDateTime; // 别名
    window.formatDateTimeForApi = formatDateTimeForApi;
    
    // DOM加载完成后自动初始化日期输入框
    function autoInitDateInputs() {
        // 如果jQuery已加载，使用jQuery的ready
        if (typeof jQuery !== 'undefined') {
            jQuery(document).ready(function() {
                // 延迟一点执行，确保所有动态内容都已加载
                setTimeout(initDateInputs, 100);
            });
        } else {
            // 否则使用原生DOM事件
            if (document.readyState === 'loading') {
                document.addEventListener('DOMContentLoaded', function() {
                    setTimeout(initDateInputs, 100);
                });
            } else {
                setTimeout(initDateInputs, 100);
            }
        }
    }
    
    // 执行自动初始化
    autoInitDateInputs();
    
    // 也导出手动初始化函数，供页面动态添加输入框后调用
    window.initDateInputs = initDateInputs;
})();

