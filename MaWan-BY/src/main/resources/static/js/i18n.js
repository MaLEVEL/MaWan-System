(() => {
  const STORAGE_KEY = 'app_lang';
  const SUPPORTED_LANGS = ['zh', 'en', 'ru'];
  const translations = {
    zh: {
      langLabel: '中文',
      langChinese: '中文',
      langEnglish: '英文',
      langRussian: '俄语',
      langRandom: '随机',
      brand: '🩸 捐献管理系统',
      navDashboard: '📊 仪表板',
      navDonors: '👥 捐献者管理',
      navMedicalChecks: '🏥 体检记录',
      navDonations: '💉 捐献记录',
      navInventory: '📦 库存管理',
      navAppointments: '📅 预约管理',
      navReports: '📈 统计报表',
      navUsers: '⚙️ 用户管理',
      logout: '退出登录',
      titleDashboard: '仪表板 - 血液与骨髓捐献管理系统',
      headingDashboard: '数据概览',
      titleDonors: '捐献者管理 - 血液与骨髓捐献管理系统',
      headingDonors: '捐献者管理',
      titleMedicalChecks: '体检记录 - 血液与骨髓捐献管理系统',
      headingMedicalChecks: '体检记录管理',
      titleDonations: '捐献记录 - 血液与骨髓捐献管理系统',
      headingDonations: '捐献记录管理',
      titleInventory: '库存管理 - 血液与骨髓捐献管理系统',
      headingInventory: '库存管理',
      titleAppointments: '预约管理 - 血液与骨髓捐献管理系统',
      headingAppointments: '预约管理',
      titleReports: '统计报表 - 血液与骨髓捐献管理系统',
      headingReports: '统计报表',
      titleUsers: '用户管理 - 血液与骨髓捐献管理系统',
      headingUsers: '用户管理',
      titleLogin: '登录 - 血液与骨髓捐献管理系统',
      headingLogin: '🩸 捐献管理系统',
      loginSubtitle: '血液与骨髓捐献管理平台',
      labelUsername: '用户名',
      labelPassword: '密码',
      labelRemember: '记住我',
      btnLogin: '登录',
      loginPlaceholderUser: '请输入用户名',
      loginPlaceholderPassword: '请输入密码',
      loginDefaultHint: '默认账户: admin / admin123',
      loginFailed: '登录失败！',
      loginErrorDefault: '登录失败，请检查用户名和密码',
      loginErrorCredentials: '用户名或密码错误',
      statTotalDonors: '总捐献者',
      statTotalDonations: '总捐献次数',
      statTotalInventory: '库存总量',
      statPendingAppointments: '待处理预约',
      sectionRecentDonationTrend: '最近捐献趋势',
      sectionBloodDistribution: '血型分布',
      sectionRecentDonationRecords: '最近捐献记录',
      thDonor: '捐献者',
      thType: '类型',
      thQuantity: '数量',
      thDate: '日期',
      thStatus: '状态',
      textLoading: '加载中...',
      textChartLoading: '图表加载中...',
      textNoData: '暂无数据',
      textUserPlaceholder: '用户',
      donorSearchPlaceholder: '搜索姓名或ID...',
      optionPleaseSelect: '请选择',
      optionAllBloodTypes: '全部血型',
      optionAllStatus: '全部状态',
      optionBloodA: 'A型',
      optionBloodB: 'B型',
      optionBloodAB: 'AB型',
      optionBloodO: 'O型',
      optionStatusActive: '活跃',
      optionStatusInactive: '非活跃',
      optionAllRh: '全部Rh',
      buttonSearch: '搜索',
      buttonAddDonor: '添加',
      thName: '姓名',
      thGender: '性别',
      thBloodType: '血型',
      thPhone: '联系电话',
      thIdNumber: 'ID',
      thBirthDate: '出生日期',
      thActions: '操作',
      modalAddDonor: '添加捐献者',
      modalEditDonor: '编辑捐献者',
      labelFullName: '姓名 *',
      hintFullName: '中文姓名可直接输入，例如“张三”；系统会自动拆分姓与名。',
      labelGender: '性别 *',
      genderMale: '男',
      genderFemale: '女',
      labelBloodType: '血型 *',
      labelRhesus: 'Rh因子 *',
      rhesusPositive: '阳性(+)',
      rhesusNegative: '阴性(-)',
      labelPhone: '联系电话 *',
      labelIdNumber: 'ID *',
      labelEmail: '邮箱',
      labelBirthDate: '出生日期 *',
      labelAddress: '地址',
      hintPhone: '请输入11位手机号码',
      hintIdNumber: '请输入8-14位数字ID',
      labelCancel: '取消',
      labelSave: '保存',
      labelClose: '关闭',
      modalDonorDetail: '捐献者详情',
      statusActiveLabel: '活跃',
      statusInactiveLabel: '非活跃',
      textUnknown: '-',
      dateFormatHint: '年/月/日',
      dateTimeFormatHint: '年/月/日',
      confirmDeleteDonor: '确定要删除这个捐献者吗？此操作不可恢复！',
      toastLoadFailed: '加载失败',
      toastLoadDetailFailed: '加载详情失败',
      toastLoadDataFailed: '加载数据失败',
      toastSaveSuccess: '保存成功！',
      toastSaveFailed: '保存失败！',
      toastDeleteSuccess: '删除成功！',
      toastDeleteFailed: '删除失败！',
      toastInputName: '请输入姓名',
      actionViewTitle: '查看详情',
      actionEditTitle: '编辑',
      actionDeleteTitle: '删除',
      detailName: '姓名',
      detailGender: '性别',
      detailBloodType: '血型',
      detailPhone: '联系电话',
      detailIdNumber: 'ID',
      detailEmail: '邮箱',
      detailBirthDate: '出生日期',
      detailStatus: '状态',
      detailAddress: '地址',
      detailHla: 'HLA类型',
      mcSearchPlaceholder: '搜索捐献者ID...',
      mcFromPlaceholder: '年/月/日',
      mcToPlaceholder: '年/月/日',
      mcButtonSearch: '搜索',
      mcButtonAdd: '添加体检记录',
      mcTableId: 'ID',
      mcTableDonor: '捐献者',
      mcTableCheckDate: '体检日期',
      mcTableHemoglobin: '血红蛋白(g/L)',
      mcTableBloodPressure: '血压(mmHg)',
      mcTableConclusion: '结论',
      mcTableActions: '操作',
      mcModalTitleAdd: '添加体检记录',
      mcModalTitleEdit: '编辑体检记录',
      mcLabelDonorId: '捐献者ID *',
      mcDonorHint: '可直接输入ID或从下拉列表中选择捐献者',
      mcLabelCheckDatetime: '体检日期时间 *',
      mcLabelHemoglobin: '血红蛋白(g/L)',
      mcLabelSystolic: '收缩压(mmHg)',
      mcLabelDiastolic: '舒张压(mmHg)',
      mcLabelConclusion: '结论 *',
      mcConclusionFit: '合格',
      mcConclusionUnfit: '不合格',
      mcConclusionRecheck: '需要复查',
      mcDetailTitle: '体检记录详情',
      mcDetailDonor: '捐献者',
      mcDetailDate: '体检日期',
      mcDetailHemoglobin: '血红蛋白',
      mcDetailBloodPressure: '血压',
      mcDetailConclusion: '结论',
      mcToastInvalidDonorId: '请输入有效的捐献者ID',
      mcToastInvalidDatetime: '请选择有效的体检日期时间',
      invFilterExpiryPlaceholder: '年/月/日',
      invFilterBloodLabel: '血型筛选',
      invFilterRhLabel: 'Rh筛选',
      invFilterTypeLabel: '类型筛选',
      invFilterStatusLabel: '状态筛选',
      invButtonSearch: '搜索',
      invButtonAdd: '添加库存',
      invButtonUpdate: '更新',
      invTableId: 'ID',
      invTableType: '类型',
      invTableBlood: '血型',
      invTableCollected: '采集日期',
      invTableExpiry: '过期日期',
      invTableStatus: '状态',
      invTableStorage: '存储位置',
      invTableActions: '操作',
      invTypeWholeBlood: '全血',
      invTypePlasma: '血浆',
      invTypePlatelets: '血小板',
      invTypeBoneMarrow: '骨髓样本',
      invStatusInStock: '在库',
      invStatusReserved: '已预留',
      invStatusUsed: '已使用',
      invStatusExpired: '已过期',
      invStatusDiscarded: '已废弃',
      invModalTitleAdd: '添加库存条目',
      invLabelDonationId: '捐献记录ID *',
      invDonationHint: '请关联一个已有的捐献记录',
      invLabelType: '库存类型 *',
      invLabelBloodGroup: '血型 *',
      invLabelRh: 'Rh因子 *',
      invLabelCollected: '采集日期 *',
      invLabelExpiry: '过期日期 *',
      invLabelStorage: '存储位置',
      invLabelComment: '备注',
      invLabelStatus: '状态 *',
      invStatusModalTitle: '更新库存状态',
      invDetailTitle: '库存详情',
      invDetailType: '类型',
      invDetailBlood: '血型',
      invDetailCollected: '采集日期',
      invDetailExpiry: '过期日期',
      invDetailStatus: '状态',
      invDetailLocation: '存储位置',
      invDetailComment: '备注',
      invToastInvalidDonationId: '请输入有效的捐献记录ID',
      apptFilterDonorPlaceholder: '捐献者ID',
      apptFilterStatusLabel: '状态筛选',
      apptFilterFromPlaceholder: '年/月/日',
      apptFilterToPlaceholder: '年/月/日',
      apptButtonSearch: '搜索',
      apptButtonAdd: '添加预约',
      apptTableId: 'ID',
      apptTableDonor: '捐献者',
      apptTableType: '类型',
      apptTableStart: '开始时间',
      apptTableEnd: '结束时间',
      apptTableLocation: '地点',
      apptTableDoctor: '医生',
      apptTableStatus: '状态',
      apptTableActions: '操作',
      apptTypeBlood: '血液',
      apptTypeBoneMarrow: '骨髓',
      apptStatusPlanned: '计划中',
      apptStatusConfirmed: '已确认',
      apptStatusCancelled: '已取消',
      apptStatusNoShow: '未到',
      apptModalTitleAdd: '添加预约',
      apptModalTitleEdit: '编辑预约',
      apptLabelDonorId: '捐献者ID *',
      apptLabelType: '预约类型 *',
      apptLabelStart: '开始时间 *',
      apptLabelEnd: '结束时间 *',
      apptLabelLocation: '地点',
      apptLabelDoctor: '医生姓名',
      apptLabelStatus: '状态',
      apptDonorHint: '可输入ID或选择已有捐献者',
      apptDetailTitle: '预约详情',
      apptDetailDonor: '捐献者',
      apptDetailType: '类型',
      apptDetailStart: '开始时间',
      apptDetailEnd: '结束时间',
      apptDetailLocation: '地点',
      apptDetailDoctor: '医生',
      apptDetailStatus: '状态',
      apptToastInvalidDonorId: '请选择有效的捐献者ID',
      apptToastDonorMissing: '该捐献者不存在，请先在捐献者管理中创建',
      apptToastInvalidTime: '请完整填写开始/结束时间',
      donStatTotalRecords: '总记录数',
      donStatPlanned: '计划中',
      donStatCompleted: '已完成',
      donStatVolume: '总捐献量(ml)',
      donFilterDonorPlaceholder: '捐献者ID',
      donFilterTypeLabel: '类型筛选',
      donFilterStatusLabel: '状态筛选',
      donFilterFromDate: '年/月/日',
      donFilterToDate: '年/月/日',
      donFilterTypeAll: '全部类型',
      donFilterStatusAll: '全部状态',
      donButtonSearch: '搜索',
      donButtonReset: '重置',
      donButtonAdd: '添加捐献记录',
      donTableId: 'ID',
      donTableDonor: '捐献者',
      donTableType: '类型',
      donTablePlannedAt: '计划时间',
      donTableCompletedAt: '完成时间',
      donTableVolume: '体积(ml)',
      donTableStatus: '状态',
      donTableActions: '操作',
      donTypeBlood: '血液',
      donTypeBoneMarrow: '骨髓',
      donStatusPlanned: '计划中',
      donStatusCompleted: '已完成',
      donStatusCancelled: '已取消',
      donStatusRejected: '已拒绝',
      donModalTitleAdd: '添加捐献记录',
      donModalLabelDonor: '捐献者 *',
      donModalSelectPlaceholder: '请选择捐献者...',
      donModalInputId: '或输入ID',
      donModalToggleInput: '输入ID',
      donModalToggleSelect: '选择捐献者',
      donModalDonorHint: '选择捐献者或直接输入ID',
      donModalType: '捐献类型 *',
      donModalPlannedAt: '计划时间 *',
      donModalVolume: '预计体积(ml)',
      donModalVolumeHint: '血液捐献通常为200-400ml，骨髓捐献可不填',
      donModalPreCheck: '体检记录ID(可选)',
      donModalNotes: '备注',
      donModalNotesPlaceholder: '其他备注信息',
      donCompleteTitle: '完成捐献',
      donCompletePerformedAt: '完成时间 *',
      donCompleteVolume: '实际体积(ml) *',
      donCompleteConfirm: '完成',
      donCancelTitle: '取消捐献',
      donCancelReason: '取消原因 *',
      donCancelReasonPlaceholder: '请输入取消原因...',
      donCancelConfirm: '确认取消',
      donDetailTitle: '捐献记录详情',
      donDetailId: '记录ID',
      donDetailDonor: '捐献者',
      donDetailType: '捐献类型',
      donDetailStatus: '状态',
      donDetailPlannedAt: '计划时间',
      donDetailPerformedAt: '完成时间',
      donDetailVolume: '体积(ml)',
      donDetailPreCheck: '体检记录ID',
      donDetailNotes: '备注',
      donToastInvalidDonor: '请选择有效的捐献者',
      donToastDonorMissing: '未找到对应的捐献者',
      donToastInvalidTime: '请选择有效的时间',
      donToastDetailFailed: '加载详情失败',
      donToastSaveSuccess: '保存成功！',
      donToastSaveFailed: '保存失败！',
      donToastCompleteSuccess: '捐献已完成！',
      donToastCompleteFailed: '操作失败！',
      donToastCancelSuccess: '捐献已取消！',
      donToastCancelFailed: '操作失败！',
      repFilterFrom: '年/月/日',
      repFilterTo: '年/月/日',
      repButtonRefresh: '刷新数据',
      repButtonReset: '重置',
      repStatTotalDonations: '总捐献次数',
      repStatBloodDonations: '血液捐献',
      repStatMarrowDonations: '骨髓捐献',
      repStatTotalVolume: '总捐献量(ml)',
      repChartDonationType: '捐献类型统计',
      repChartInventory: '库存按血型统计',
      repChartInventorySeries: '库存数量',
      repToastDonationFail: '加载捐献统计失败',
      repToastInventoryFail: '加载库存统计失败',
      usrFilterPlaceholder: '搜索用户名...',
      usrButtonSearch: '搜索',
      usrButtonAdd: '添加用户',
      usrTableId: 'ID',
      usrTableUsername: '用户名',
      usrTableFullName: '姓名',
      usrTableEmail: '邮箱',
      usrTableRoles: '角色',
      usrTableStatus: '状态',
      usrTableActions: '操作',
      usrStatusEnabled: '启用',
      usrStatusDisabled: '禁用',
      usrModalTitle: '添加用户',
      usrLabelUsername: '用户名 *',
      usrLabelPassword: '密码 *',
      usrLabelFullName: '姓名 *',
      usrLabelEmail: '邮箱',
      usrLabelRoles: '角色 *',
      usrPasswordHint: '密码至少6位字符',
      usrRoleAdmin: '管理员',
      usrRoleDoctor: '医生',
      usrRoleLabTech: '实验室技术员',
      usrRoleReportViewer: '报表查看者',
      usrActionView: '查看详情',
      usrDetailTitle: '用户详情',
      usrDetailUsername: '用户名',
      usrDetailFullName: '姓名',
      usrDetailEmail: '邮箱',
      usrDetailRoles: '角色',
      usrDetailStatus: '状态',
      usrToastSelectRole: '请至少选择一个角色',
      usrToastForbidden: '权限不足，仅管理员可访问',
      usrToastLoadFailed: '加载失败',
      usrToastDetailFailed: '加载详情失败',
      usrToastCreateSuccess: '用户创建成功！',
      usrToastCreateFailed: '创建失败！'
    },
    en: {
      langLabel: 'EN',
      langChinese: 'Chinese',
      langEnglish: 'English',
      langRussian: 'Russian',
      langRandom: 'Random',
      brand: '🩸 Donation Management',
      navDashboard: '📊 Dashboard',
      navDonors: '👥 Donors',
      navMedicalChecks: '🏥 Medical Checks',
      navDonations: '💉 Donations',
      navInventory: '📦 Inventory',
      navAppointments: '📅 Appointments',
      navReports: '📈 Reports',
      navUsers: '⚙️ Users',
      logout: 'Logout',
      titleDashboard: 'Dashboard - Blood & Marrow Donation',
      headingDashboard: 'Data Overview',
      titleDonors: 'Donor Management - Blood & Marrow Donation',
      headingDonors: 'Donor Management',
      titleMedicalChecks: 'Medical Checks - Blood & Marrow Donation',
      headingMedicalChecks: 'Medical Check Records',
      titleDonations: 'Donation Records - Blood & Marrow Donation',
      headingDonations: 'Donation Records',
      titleInventory: 'Inventory - Blood & Marrow Donation',
      headingInventory: 'Inventory Management',
      titleAppointments: 'Appointments - Blood & Marrow Donation',
      headingAppointments: 'Appointment Management',
      titleReports: 'Reports - Blood & Marrow Donation',
      headingReports: 'Analytics & Reports',
      titleUsers: 'User Management - Blood & Marrow Donation',
      headingUsers: 'User Management',
      titleLogin: 'Sign In - Blood & Marrow Donation',
      headingLogin: '🩸 Donation Management',
      loginSubtitle: 'Blood & Bone Marrow Platform',
      labelUsername: 'Username',
      labelPassword: 'Password',
      labelRemember: 'Remember me',
      btnLogin: 'Sign In',
      loginPlaceholderUser: 'Enter username',
      loginPlaceholderPassword: 'Enter password',
      loginDefaultHint: 'Default account: admin / admin123',
      loginFailed: 'Login failed!',
      loginErrorDefault: 'Login failed, please check your username and password',
      loginErrorCredentials: 'Incorrect username or password',
      statTotalDonors: 'Total Donors',
      statTotalDonations: 'Total Donations',
      statTotalInventory: 'Inventory Units',
      statPendingAppointments: 'Pending Appointments',
      sectionRecentDonationTrend: 'Recent Donation Trend',
      sectionBloodDistribution: 'Blood Type Distribution',
      sectionRecentDonationRecords: 'Recent Donation Records',
      thDonor: 'Donor',
      thType: 'Type',
      thQuantity: 'Quantity',
      thDate: 'Date',
      thStatus: 'Status',
      textLoading: 'Loading...',
      textChartLoading: 'Chart loading...',
      textNoData: 'No data',
      textUserPlaceholder: 'User',
      donorSearchPlaceholder: 'Search by name or ID...',
      optionPleaseSelect: 'Please select',
      optionAllBloodTypes: 'All blood types',
      optionAllStatus: 'All statuses',
      optionBloodA: 'Type A',
      optionBloodB: 'Type B',
      optionBloodAB: 'Type AB',
      optionBloodO: 'Type O',
      optionStatusActive: 'Active',
      optionStatusInactive: 'Inactive',
      optionAllRh: 'All Rh',
      buttonSearch: 'Search',
      buttonAddDonor: 'Add',
      thName: 'Name',
      thGender: 'Gender',
      thBloodType: 'Blood Type',
      thPhone: 'Phone',
      thIdNumber: 'ID',
      thBirthDate: 'Birth Date',
      thActions: 'Actions',
      modalAddDonor: 'Add Donor',
      modalEditDonor: 'Edit Donor',
      labelFullName: 'Full Name *',
      hintFullName: 'Chinese names can be entered directly, e.g. “张三”; the system will split first/last names.',
      labelGender: 'Gender *',
      genderMale: 'Male',
      genderFemale: 'Female',
      labelBloodType: 'Blood Type *',
      labelRhesus: 'Rh Factor *',
      rhesusPositive: 'Positive (+)',
      rhesusNegative: 'Negative (-)',
      labelPhone: 'Phone *',
      labelIdNumber: 'ID *',
      labelEmail: 'Email',
      labelBirthDate: 'Date of Birth *',
      labelAddress: 'Address',
      hintPhone: 'Enter an 11-digit phone number',
      hintIdNumber: 'Enter 8-14 digit ID',
      labelCancel: 'Cancel',
      labelSave: 'Save',
      labelClose: 'Close',
      modalDonorDetail: 'Donor Details',
      statusActiveLabel: 'Active',
      statusInactiveLabel: 'Inactive',
      textUnknown: '-',
      confirmDeleteDonor: 'Delete this donor? This action cannot be undone!',
      toastLoadFailed: 'Load failed',
      toastLoadDetailFailed: 'Failed to load details',
      toastLoadDataFailed: 'Failed to load data',
      toastSaveSuccess: 'Saved successfully!',
      toastSaveFailed: 'Save failed!',
      toastDeleteSuccess: 'Deleted successfully!',
      toastDeleteFailed: 'Delete failed!',
      toastInputName: 'Please enter a name',
      actionViewTitle: 'View details',
      actionEditTitle: 'Edit',
      actionDeleteTitle: 'Delete',
      detailName: 'Name',
      detailGender: 'Gender',
      detailBloodType: 'Blood Type',
      detailPhone: 'Phone',
      detailIdNumber: 'ID',
      detailEmail: 'Email',
      detailBirthDate: 'Date of Birth',
      detailStatus: 'Status',
      detailAddress: 'Address',
      detailHla: 'HLA Type',
      dateFormatHint: 'YYYY/MM/DD',
      dateTimeFormatHint: 'YYYY/MM/DD',
      mcSearchPlaceholder: 'Search donor ID...',
      mcFromPlaceholder: 'YYYY/MM/DD',
      mcToPlaceholder: 'YYYY/MM/DD',
      mcButtonSearch: 'Search',
      mcButtonAdd: 'Add medical check',
      mcTableId: 'ID',
      mcTableDonor: 'Donor',
      mcTableCheckDate: 'Check Date',
      mcTableHemoglobin: 'Hemoglobin (g/L)',
      mcTableBloodPressure: 'Blood Pressure (mmHg)',
      mcTableConclusion: 'Conclusion',
      mcTableActions: 'Actions',
      mcModalTitleAdd: 'Add Medical Check',
      mcModalTitleEdit: 'Edit Medical Check',
      mcLabelDonorId: 'Donor ID *',
      mcDonorHint: 'Enter ID directly or choose from the list',
      mcLabelCheckDatetime: 'Check Date & Time *',
      mcLabelHemoglobin: 'Hemoglobin (g/L)',
      mcLabelSystolic: 'Systolic (mmHg)',
      mcLabelDiastolic: 'Diastolic (mmHg)',
      mcLabelConclusion: 'Conclusion *',
      mcConclusionFit: 'Fit',
      mcConclusionUnfit: 'Unfit',
      mcConclusionRecheck: 'Needs recheck',
      mcDetailTitle: 'Medical Check Details',
      mcDetailDonor: 'Donor',
      mcDetailDate: 'Check Date',
      mcDetailHemoglobin: 'Hemoglobin',
      mcDetailBloodPressure: 'Blood Pressure',
      mcDetailConclusion: 'Conclusion',
      mcToastInvalidDonorId: 'Please enter a valid donor ID',
      mcToastInvalidDatetime: 'Please pick a valid check date/time',
      invFilterExpiryPlaceholder: 'YYYY/MM/DD',
      invFilterBloodLabel: 'Blood filter',
      invFilterRhLabel: 'Rh filter',
      invFilterTypeLabel: 'Type filter',
      invFilterStatusLabel: 'Status filter',
      invButtonSearch: 'Search',
      invButtonAdd: 'Add inventory',
      invButtonUpdate: 'Update',
      invTableId: 'ID',
      invTableType: 'Type',
      invTableBlood: 'Blood',
      invTableCollected: 'Collected At',
      invTableExpiry: 'Expiry Date',
      invTableStatus: 'Status',
      invTableStorage: 'Storage',
      invTableActions: 'Actions',
      invTypeWholeBlood: 'Whole blood',
      invTypePlasma: 'Plasma',
      invTypePlatelets: 'Platelets',
      invTypeBoneMarrow: 'Bone marrow sample',
      invStatusInStock: 'In stock',
      invStatusReserved: 'Reserved',
      invStatusUsed: 'Used',
      invStatusExpired: 'Expired',
      invStatusDiscarded: 'Discarded',
      invModalTitleAdd: 'Add Inventory Item',
      invLabelDonationId: 'Donation Record ID *',
      invDonationHint: 'Please link to an existing donation record',
      invLabelType: 'Inventory Type *',
      invLabelBloodGroup: 'Blood Group *',
      invLabelRh: 'Rh Factor *',
      invLabelCollected: 'Collection Date *',
      invLabelExpiry: 'Expiry Date *',
      invLabelStorage: 'Storage Location',
      invLabelComment: 'Comment',
      invLabelStatus: 'Status *',
      invStatusModalTitle: 'Update Inventory Status',
      invDetailTitle: 'Inventory Details',
      invDetailType: 'Type',
      invDetailBlood: 'Blood Type',
      invDetailCollected: 'Collected At',
      invDetailExpiry: 'Expiry Date',
      invDetailStatus: 'Status',
      invDetailLocation: 'Storage Location',
      invDetailComment: 'Comment',
      invToastInvalidDonationId: 'Please enter a valid donation record ID',
      apptFilterDonorPlaceholder: 'Donor ID',
      apptFilterStatusLabel: 'Status filter',
      apptFilterFromPlaceholder: 'YYYY/MM/DD',
      apptFilterToPlaceholder: 'YYYY/MM/DD',
      apptButtonSearch: 'Search',
      apptButtonAdd: 'Add appointment',
      apptTableId: 'ID',
      apptTableDonor: 'Donor',
      apptTableType: 'Type',
      apptTableStart: 'Start Time',
      apptTableEnd: 'End Time',
      apptTableLocation: 'Location',
      apptTableDoctor: 'Doctor',
      apptTableStatus: 'Status',
      apptTableActions: 'Actions',
      apptTypeBlood: 'Blood',
      apptTypeBoneMarrow: 'Bone marrow',
      apptStatusPlanned: 'Planned',
      apptStatusConfirmed: 'Confirmed',
      apptStatusCancelled: 'Cancelled',
      apptStatusNoShow: 'No-show',
      apptModalTitleAdd: 'Add Appointment',
      apptModalTitleEdit: 'Edit Appointment',
      apptLabelDonorId: 'Donor ID *',
      apptLabelType: 'Appointment Type *',
      apptLabelStart: 'Start Time *',
      apptLabelEnd: 'End Time *',
      apptLabelLocation: 'Location',
      apptLabelDoctor: 'Doctor Name',
      apptLabelStatus: 'Status',
      apptDonorHint: 'Enter ID or choose an existing donor',
      apptDetailTitle: 'Appointment Details',
      apptDetailDonor: 'Donor',
      apptDetailType: 'Type',
      apptDetailStart: 'Start Time',
      apptDetailEnd: 'End Time',
      apptDetailLocation: 'Location',
      apptDetailDoctor: 'Doctor',
      apptDetailStatus: 'Status',
      apptToastInvalidDonorId: 'Please enter a valid donor ID',
      apptToastDonorMissing: 'The donor does not exist, please create it first',
      apptToastInvalidTime: 'Please complete start/end time',
      donStatTotalRecords: 'Total records',
      donStatPlanned: 'Planned',
      donStatCompleted: 'Completed',
      donStatVolume: 'Total volume (ml)',
      donFilterDonorPlaceholder: 'Donor ID',
      donFilterTypeLabel: 'Type filter',
      donFilterStatusLabel: 'Status filter',
      donFilterFromDate: 'YYYY/MM/DD',
      donFilterToDate: 'YYYY/MM/DD',
      donFilterTypeAll: 'All types',
      donFilterStatusAll: 'All statuses',
      donButtonSearch: 'Search',
      donButtonReset: 'Reset',
      donButtonAdd: 'Add donation',
      donTableId: 'ID',
      donTableDonor: 'Donor',
      donTableType: 'Type',
      donTablePlannedAt: 'Planned at',
      donTableCompletedAt: 'Completed at',
      donTableVolume: 'Volume (ml)',
      donTableStatus: 'Status',
      donTableActions: 'Actions',
      donTypeBlood: 'Blood',
      donTypeBoneMarrow: 'Bone marrow',
      donStatusPlanned: 'Planned',
      donStatusCompleted: 'Completed',
      donStatusCancelled: 'Cancelled',
      donStatusRejected: 'Rejected',
      donModalTitleAdd: 'Add Donation',
      donModalLabelDonor: 'Donor *',
      donModalSelectPlaceholder: 'Select donor...',
      donModalInputId: 'Or enter ID',
      donModalToggleInput: 'Enter ID',
      donModalToggleSelect: 'Select donor',
      donModalDonorHint: 'Select a donor or enter the ID directly',
      donModalType: 'Donation Type *',
      donModalPlannedAt: 'Planned Time *',
      donModalVolume: 'Estimated Volume (ml)',
      donModalVolumeHint: 'Blood donation is usually 200-400ml; marrow donations can be left blank',
      donModalPreCheck: 'Medical Check ID (optional)',
      donModalNotes: 'Notes',
      donModalNotesPlaceholder: 'Additional notes',
      donCompleteTitle: 'Complete Donation',
      donCompletePerformedAt: 'Completion Time *',
      donCompleteVolume: 'Actual Volume (ml) *',
      donCompleteConfirm: 'Complete',
      donCancelTitle: 'Cancel Donation',
      donCancelReason: 'Cancellation Reason *',
      donCancelReasonPlaceholder: 'Please enter a cancellation reason...',
      donCancelConfirm: 'Confirm cancel',
      donDetailTitle: 'Donation Details',
      donDetailId: 'Record ID',
      donDetailDonor: 'Donor',
      donDetailType: 'Donation Type',
      donDetailStatus: 'Status',
      donDetailPlannedAt: 'Planned Time',
      donDetailPerformedAt: 'Completion Time',
      donDetailVolume: 'Volume (ml)',
      donDetailPreCheck: 'Medical Check ID',
      donDetailNotes: 'Notes',
      donToastInvalidDonor: 'Please choose a valid donor',
      donToastDonorMissing: 'Donor not found',
      donToastInvalidTime: 'Please provide valid times',
      donToastDetailFailed: 'Failed to load donation detail',
      donToastSaveSuccess: 'Donation saved successfully!',
      donToastSaveFailed: 'Failed to save donation',
      donToastCompleteSuccess: 'Donation completed!',
      donToastCompleteFailed: 'Failed to complete donation',
      donToastCancelSuccess: 'Donation cancelled!',
      donToastCancelFailed: 'Failed to cancel donation',
      repFilterFrom: 'YYYY/MM/DD',
      repFilterTo: 'YYYY/MM/DD',
      repButtonRefresh: 'Refresh',
      repButtonReset: 'Reset',
      repStatTotalDonations: 'Total donations',
      repStatBloodDonations: 'Blood donations',
      repStatMarrowDonations: 'Marrow donations',
      repStatTotalVolume: 'Total volume (ml)',
      repChartDonationType: 'Donation Type Breakdown',
      repChartInventory: 'Inventory by Blood Type',
      repChartInventorySeries: 'Units in stock',
      repToastDonationFail: 'Failed to load donation summary',
      repToastInventoryFail: 'Failed to load inventory summary',
      usrFilterPlaceholder: 'Search username...',
      usrButtonSearch: 'Search',
      usrButtonAdd: 'Add user',
      usrTableId: 'ID',
      usrTableUsername: 'Username',
      usrTableFullName: 'Full Name',
      usrTableEmail: 'Email',
      usrTableRoles: 'Roles',
      usrTableStatus: 'Status',
      usrTableActions: 'Actions',
      usrStatusEnabled: 'Enabled',
      usrStatusDisabled: 'Disabled',
      usrModalTitle: 'Add User',
      usrLabelUsername: 'Username *',
      usrLabelPassword: 'Password *',
      usrLabelFullName: 'Full Name *',
      usrLabelEmail: 'Email',
      usrLabelRoles: 'Roles *',
      usrPasswordHint: 'Password must be at least 6 characters',
      usrRoleAdmin: 'Administrator',
      usrRoleDoctor: 'Doctor',
      usrRoleLabTech: 'Lab technician',
      usrRoleReportViewer: 'Report viewer',
      usrActionView: 'View details',
      usrDetailTitle: 'User Details',
      usrDetailUsername: 'Username',
      usrDetailFullName: 'Full name',
      usrDetailEmail: 'Email',
      usrDetailRoles: 'Roles',
      usrDetailStatus: 'Status',
      usrToastSelectRole: 'Select at least one role',
      usrToastForbidden: 'Insufficient permissions, admin only',
      usrToastLoadFailed: 'Failed to load data',
      usrToastDetailFailed: 'Failed to load details',
      usrToastCreateSuccess: 'User created successfully!',
      usrToastCreateFailed: 'Failed to create user'
    },
    ru: {
      langLabel: 'РУС',
      langChinese: 'Китайский',
      langEnglish: 'Английский',
      langRussian: 'Русский',
      langRandom: 'Случайно',
      brand: '🩸 Система донорства',
      navDashboard: '📊 Дашборд',
      navDonors: '👥 Доноры',
      navMedicalChecks: '🏥 Медосмотры',
      navDonations: '💉 Пожертвования',
      navInventory: '📦 Инвентарь',
      navAppointments: '📅 Записи',
      navReports: '📈 Отчёты',
      navUsers: '⚙️ Пользователи',
      logout: 'Выйти',
      titleDashboard: 'Дашборд - Система донорства',
      headingDashboard: 'Обзор данных',
      titleDonors: 'Доноры - Система донорства',
      headingDonors: 'Управление донорами',
      titleMedicalChecks: 'Медосмотры - Система донорства',
      headingMedicalChecks: 'Журнал медосмотров',
      titleDonations: 'Пожертвования - Система донорства',
      headingDonations: 'Управление пожертвованиями',
      titleInventory: 'Инвентарь - Система донорства',
      headingInventory: 'Управление запасами',
      titleAppointments: 'Записи - Система донорства',
      headingAppointments: 'Управление записями',
      titleReports: 'Отчёты - Система донорства',
      headingReports: 'Статистические отчёты',
      titleUsers: 'Пользователи - Система донорства',
      headingUsers: 'Управление пользователями',
      titleLogin: 'Вход - Система донорства',
      headingLogin: '🩸 Система донорства',
      loginSubtitle: 'Платформа крови и костного мозга',
      labelUsername: 'Имя пользователя',
      labelPassword: 'Пароль',
      labelRemember: 'Запомнить меня',
      btnLogin: 'Войти',
      loginPlaceholderUser: 'Введите имя пользователя',
      loginPlaceholderPassword: 'Введите пароль',
      loginDefaultHint: 'Учётная запись по умолчанию: admin / admin123',
      loginFailed: 'Ошибка входа!',
      loginErrorDefault: 'Не удалось войти, проверьте имя пользователя и пароль',
      loginErrorCredentials: 'Неверное имя пользователя или пароль',
      statTotalDonors: 'Всего доноров',
      statTotalDonations: 'Всего пожертвований',
      statTotalInventory: 'Запасы',
      statPendingAppointments: 'Ожидающие записи',
      sectionRecentDonationTrend: 'Динамика пожертвований',
      sectionBloodDistribution: 'Распределение по группам крови',
      sectionRecentDonationRecords: 'Последние пожертвования',
      thDonor: 'Донор',
      thType: 'Тип',
      thQuantity: 'Количество',
      thDate: 'Дата',
      thStatus: 'Статус',
      textLoading: 'Загрузка...',
      textChartLoading: 'График загружается...',
      textNoData: 'Нет данных',
      textUserPlaceholder: 'Пользователь',
      donorSearchPlaceholder: 'Поиск по имени или ID...',
      optionPleaseSelect: 'Выберите',
      optionAllBloodTypes: 'Все группы крови',
      optionAllStatus: 'Все статусы',
      optionBloodA: 'Группа A',
      optionBloodB: 'Группа B',
      optionBloodAB: 'Группа AB',
      optionBloodO: 'Группа O',
      optionStatusActive: 'Активен',
      optionStatusInactive: 'Неактивен',
      optionAllRh: 'Все Rh',
      buttonSearch: 'Поиск',
      buttonAddDonor: 'Добавить',
      thName: 'Имя',
      thGender: 'Пол',
      thBloodType: 'Группа крови',
      thPhone: 'Телефон',
      thIdNumber: 'ID',
      thBirthDate: 'Дата рождения',
      thActions: 'Действия',
      modalAddDonor: 'Добавить донора',
      modalEditDonor: 'Редактировать донора',
      labelFullName: 'Имя *',
      hintFullName: 'Китайские имена можно вводить полностью, например «张三» — система разделит автоматически.',
      labelGender: 'Пол *',
      genderMale: 'Муж',
      genderFemale: 'Жен',
      labelBloodType: 'Группа крови *',
      labelRhesus: 'Резус-фактор *',
      rhesusPositive: 'Положительный (+)',
      rhesusNegative: 'Отрицательный (-)',
      labelPhone: 'Телефон *',
      labelIdNumber: 'ID *',
      labelEmail: 'Email',
      labelBirthDate: 'Дата рождения *',
      labelAddress: 'Адрес',
      hintPhone: 'Введите 11-значный номер телефона',
      hintIdNumber: 'Введите 8-14 цифр ID',
      labelCancel: 'Отмена',
      labelSave: 'Сохранить',
      labelClose: 'Закрыть',
      modalDonorDetail: 'Данные донора',
      statusActiveLabel: 'Активен',
      statusInactiveLabel: 'Неактивен',
      textUnknown: '-',
      confirmDeleteDonor: 'Удалить этого донора? Действие необратимо!',
      toastLoadFailed: 'Ошибка загрузки',
      toastLoadDetailFailed: 'Не удалось загрузить детали',
      toastLoadDataFailed: 'Не удалось загрузить данные',
      toastSaveSuccess: 'Успешно сохранено!',
      toastSaveFailed: 'Ошибка сохранения!',
      toastDeleteSuccess: 'Успешно удалено!',
      toastDeleteFailed: 'Ошибка удаления!',
      toastInputName: 'Введите имя',
      actionViewTitle: 'Подробнее',
      actionEditTitle: 'Редактировать',
      actionDeleteTitle: 'Удалить',
      detailName: 'Имя',
      detailGender: 'Пол',
      detailBloodType: 'Группа крови',
      detailPhone: 'Телефон',
      detailIdNumber: 'ID',
      detailEmail: 'Email',
      detailBirthDate: 'Дата рождения',
      detailStatus: 'Статус',
      detailAddress: 'Адрес',
      detailHla: 'HLA тип',
      dateFormatHint: 'ГГ/ММ/ДД',
      dateTimeFormatHint: 'ГГ/ММ/ДД',
      mcSearchPlaceholder: 'Поиск ID донора...',
      mcFromPlaceholder: 'ГГ/ММ/ДД',
      mcToPlaceholder: 'ГГ/ММ/ДД',
      mcButtonSearch: 'Поиск',
      mcButtonAdd: 'Добавить медосмотр',
      mcTableId: 'ID',
      mcTableDonor: 'Донор',
      mcTableCheckDate: 'Дата осмотра',
      mcTableHemoglobin: 'Гемоглобин (г/л)',
      mcTableBloodPressure: 'Давление (мм рт. ст.)',
      mcTableConclusion: 'Заключение',
      mcTableActions: 'Действия',
      mcModalTitleAdd: 'Добавить медосмотр',
      mcModalTitleEdit: 'Редактировать медосмотр',
      mcLabelDonorId: 'ID донора *',
      mcDonorHint: 'Введите ID или выберите из списка',
      mcLabelCheckDatetime: 'Дата и время осмотра *',
      mcLabelHemoglobin: 'Гемоглобин (г/л)',
      mcLabelSystolic: 'Систолическое давление',
      mcLabelDiastolic: 'Диастолическое давление',
      mcLabelConclusion: 'Заключение *',
      mcConclusionFit: 'Годен',
      mcConclusionUnfit: 'Не годен',
      mcConclusionRecheck: 'Нужен пересмотр',
      mcDetailTitle: 'Данные медосмотра',
      mcDetailDonor: 'Донор',
      mcDetailDate: 'Дата осмотра',
      mcDetailHemoglobin: 'Гемоглобин',
      mcDetailBloodPressure: 'Давление',
      mcDetailConclusion: 'Заключение',
      mcToastInvalidDonorId: 'Введите корректный ID донора',
      mcToastInvalidDatetime: 'Выберите корректную дату и время',
      invFilterExpiryPlaceholder: 'ГГ/ММ/ДД',
      invFilterBloodLabel: 'Фильтр по крови',
      invFilterRhLabel: 'Фильтр по Rh',
      invFilterTypeLabel: 'Фильтр по типу',
      invFilterStatusLabel: 'Фильтр по статусу',
      invButtonSearch: 'Поиск',
      invButtonAdd: 'Добавить запись',
      invButtonUpdate: 'Обновить',
      invTableId: 'ID',
      invTableType: 'Тип',
      invTableBlood: 'Группа крови',
      invTableCollected: 'Дата сбора',
      invTableExpiry: 'Дата истечения',
      invTableStatus: 'Статус',
      invTableStorage: 'Место хранения',
      invTableActions: 'Действия',
      invTypeWholeBlood: 'Цельная кровь',
      invTypePlasma: 'Плазма',
      invTypePlatelets: 'Тромбоциты',
      invTypeBoneMarrow: 'Образец костного мозга',
      invStatusInStock: 'На складе',
      invStatusReserved: 'Зарезервировано',
      invStatusUsed: 'Использовано',
      invStatusExpired: 'Просрочено',
      invStatusDiscarded: 'Списано',
      invModalTitleAdd: 'Добавить запись склада',
      invLabelDonationId: 'ID записи пожертвования *',
      invDonationHint: 'Свяжите с существующей записью пожертвования',
      invLabelType: 'Тип запаса *',
      invLabelBloodGroup: 'Группа крови *',
      invLabelRh: 'Резус-фактор *',
      invLabelCollected: 'Дата сбора *',
      invLabelExpiry: 'Дата истечения *',
      invLabelStorage: 'Место хранения',
      invLabelComment: 'Комментарий',
      invLabelStatus: 'Статус *',
      invStatusModalTitle: 'Обновить статус запаса',
      invDetailTitle: 'Детали запаса',
      invDetailType: 'Тип',
      invDetailBlood: 'Группа крови',
      invDetailCollected: 'Дата сбора',
      invDetailExpiry: 'Дата истечения',
      invDetailStatus: 'Статус',
      invDetailLocation: 'Место хранения',
      invDetailComment: 'Комментарий',
      invToastInvalidDonationId: 'Введите корректный ID записи пожертвования',
      apptFilterDonorPlaceholder: 'ID донора',
      apptFilterStatusLabel: 'Фильтр статуса',
      apptFilterFromPlaceholder: 'ГГ/ММ/ДД',
      apptFilterToPlaceholder: 'ГГ/ММ/ДД',
      apptButtonSearch: 'Поиск',
      apptButtonAdd: 'Добавить запись',
      apptTableId: 'ID',
      apptTableDonor: 'Донор',
      apptTableType: 'Тип',
      apptTableStart: 'Начало',
      apptTableEnd: 'Окончание',
      apptTableLocation: 'Место',
      apptTableDoctor: 'Врач',
      apptTableStatus: 'Статус',
      apptTableActions: 'Действия',
      apptTypeBlood: 'Кровь',
      apptTypeBoneMarrow: 'Костный мозг',
      apptStatusPlanned: 'Запланировано',
      apptStatusConfirmed: 'Подтверждено',
      apptStatusCancelled: 'Отменено',
      apptStatusNoShow: 'Неявка',
      apptModalTitleAdd: 'Добавить запись',
      apptModalTitleEdit: 'Редактировать запись',
      apptLabelDonorId: 'ID донора *',
      apptLabelType: 'Тип записи *',
      apptLabelStart: 'Время начала *',
      apptLabelEnd: 'Время окончания *',
      apptLabelLocation: 'Место',
      apptLabelDoctor: 'Имя врача',
      apptLabelStatus: 'Статус',
      apptDonorHint: 'Введите ID или выберите существующего донора',
      apptDetailTitle: 'Детали записи',
      apptDetailDonor: 'Донор',
      apptDetailType: 'Тип',
      apptDetailStart: 'Начало',
      apptDetailEnd: 'Окончание',
      apptDetailLocation: 'Место',
      apptDetailDoctor: 'Врач',
      apptDetailStatus: 'Статус',
      apptToastInvalidDonorId: 'Введите корректный ID донора',
      apptToastDonorMissing: 'Донор не найден, создайте его сначала',
      apptToastInvalidTime: 'Заполните время начала и окончания',
      donStatTotalRecords: 'Всего записей',
      donStatPlanned: 'Запланировано',
      donStatCompleted: 'Завершено',
      donStatVolume: 'Общий объём (мл)',
      donFilterDonorPlaceholder: 'ID донора',
      donFilterTypeLabel: 'Фильтр типа',
      donFilterStatusLabel: 'Фильтр статуса',
      donFilterFromDate: 'ГГ/ММ/ДД',
      donFilterToDate: 'ГГ/ММ/ДД',
      donFilterTypeAll: 'Все типы',
      donFilterStatusAll: 'Все статусы',
      donButtonSearch: 'Поиск',
      donButtonReset: 'Сброс',
      donButtonAdd: 'Добавить запись',
      donTableId: 'ID',
      donTableDonor: 'Донор',
      donTableType: 'Тип',
      donTablePlannedAt: 'Плановое время',
      donTableCompletedAt: 'Время завершения',
      donTableVolume: 'Объём (мл)',
      donTableStatus: 'Статус',
      donTableActions: 'Действия',
      donTypeBlood: 'Кровь',
      donTypeBoneMarrow: 'Костный мозг',
      donStatusPlanned: 'Запланировано',
      donStatusCompleted: 'Завершено',
      donStatusCancelled: 'Отменено',
      donStatusRejected: 'Отклонено',
      donModalTitleAdd: 'Добавить запись',
      donModalLabelDonor: 'Донор *',
      donModalSelectPlaceholder: 'Выберите донора...',
      donModalInputId: 'Или введите ID',
      donModalToggleInput: 'Ввести ID',
      donModalToggleSelect: 'Выбрать донора',
      donModalDonorHint: 'Выберите донора или укажите ID вручную',
      donModalType: 'Тип пожертвования *',
      donModalPlannedAt: 'Плановое время *',
      donModalVolume: 'Ожидаемый объём (мл)',
      donModalVolumeHint: 'Кровь обычно 200-400 мл; для костного мозга можно не заполнять',
      donModalPreCheck: 'ID медосмотра (опц.)',
      donModalNotes: 'Примечания',
      donModalNotesPlaceholder: 'Дополнительные примечания',
      donCompleteTitle: 'Завершить пожертвование',
      donCompletePerformedAt: 'Время завершения *',
      donCompleteVolume: 'Фактический объём (мл) *',
      donCompleteConfirm: 'Завершить',
      donCancelTitle: 'Отменить пожертвование',
      donCancelReason: 'Причина отмены *',
      donCancelReasonPlaceholder: 'Укажите причину отмены...',
      donCancelConfirm: 'Подтвердить отмену',
      donDetailTitle: 'Данные пожертвования',
      donDetailId: 'ID записи',
      donDetailDonor: 'Донор',
      donDetailType: 'Тип пожертвования',
      donDetailStatus: 'Статус',
      donDetailPlannedAt: 'Плановое время',
      donDetailPerformedAt: 'Время завершения',
      donDetailVolume: 'Объём (мл)',
      donDetailPreCheck: 'ID медосмотра',
      donDetailNotes: 'Примечания',
      donToastInvalidDonor: 'Выберите корректного донора',
      donToastDonorMissing: 'Донор не найден',
      donToastInvalidTime: 'Укажите корректное время',
      donToastDetailFailed: 'Не удалось загрузить детали',
      donToastSaveSuccess: 'Запись успешно сохранена!',
      donToastSaveFailed: 'Не удалось сохранить запись',
      donToastCompleteSuccess: 'Пожертвование завершено!',
      donToastCompleteFailed: 'Не удалось завершить пожертвование',
      donToastCancelSuccess: 'Пожертвование отменено!',
      donToastCancelFailed: 'Не удалось отменить пожертвование',
      repFilterFrom: 'ГГ/ММ/ДД',
      repFilterTo: 'ГГ/ММ/ДД',
      repButtonRefresh: 'Обновить',
      repButtonReset: 'Сброс',
      repStatTotalDonations: 'Всего пожертвований',
      repStatBloodDonations: 'Пожертвования крови',
      repStatMarrowDonations: 'Пожертвования костного мозга',
      repStatTotalVolume: 'Общий объём (мл)',
      repChartDonationType: 'Статистика по типам',
      repChartInventory: 'Запасы по группам крови',
      repChartInventorySeries: 'Единиц на складе',
      repToastDonationFail: 'Не удалось загрузить статистику пожертвований',
      repToastInventoryFail: 'Не удалось загрузить статистику запасов',
      usrFilterPlaceholder: 'Поиск по имени...',
      usrButtonSearch: 'Поиск',
      usrButtonAdd: 'Добавить пользователя',
      usrTableId: 'ID',
      usrTableUsername: 'Имя пользователя',
      usrTableFullName: 'ФИО',
      usrTableEmail: 'Email',
      usrTableRoles: 'Роли',
      usrTableStatus: 'Статус',
      usrTableActions: 'Действия',
      usrStatusEnabled: 'Включен',
      usrStatusDisabled: 'Выключен',
      usrModalTitle: 'Добавить пользователя',
      usrLabelUsername: 'Имя пользователя *',
      usrLabelPassword: 'Пароль *',
      usrLabelFullName: 'ФИО *',
      usrLabelEmail: 'Email',
      usrLabelRoles: 'Роли *',
      usrPasswordHint: 'Пароль должен содержать минимум 6 символов',
      usrRoleAdmin: 'Администратор',
      usrRoleDoctor: 'Врач',
      usrRoleLabTech: 'Лаборант',
      usrRoleReportViewer: 'Просмотр отчётов',
      usrActionView: 'Просмотреть детали',
      usrDetailTitle: 'Информация о пользователе',
      usrDetailUsername: 'Имя пользователя',
      usrDetailFullName: 'ФИО',
      usrDetailEmail: 'Email',
      usrDetailRoles: 'Роли',
      usrDetailStatus: 'Статус',
      usrToastSelectRole: 'Выберите хотя бы одну роль',
      usrToastForbidden: 'Недостаточно прав, только для администраторов',
      usrToastLoadFailed: 'Не удалось загрузить данные',
      usrToastDetailFailed: 'Не удалось загрузить детали',
      usrToastCreateSuccess: 'Пользователь успешно создан!',
      usrToastCreateFailed: 'Не удалось создать пользователя'
    }
  };

  let currentLang = localStorage.getItem(STORAGE_KEY) || 'zh';

  function resolveLanguage(lang) {
    if (lang === 'random') {
      const pool = SUPPORTED_LANGS.filter(l => l !== currentLang);
      return pool[Math.floor(Math.random() * pool.length)] || currentLang;
    }
    return SUPPORTED_LANGS.includes(lang) ? lang : 'zh';
  }

  function translateKey(key, lang = currentLang) {
    const dict = translations[lang] || translations.zh;
    if (!dict) return key;
    return dict[key] || translations.zh[key] || key;
  }

  function applyTranslations(lang) {
    const dict = translations[lang];
    if (!dict) return;

    if (document.documentElement) {
      document.documentElement.setAttribute('lang', lang);
    }

    document.querySelectorAll('[data-i18n]').forEach(el => {
      const key = el.dataset.i18n;
      if (dict[key]) {
        el.innerHTML = dict[key];
      }
    });

    document.querySelectorAll('[data-i18n-placeholder]').forEach(el => {
      const key = el.dataset.i18nPlaceholder;
      // 对于日期时间输入框，根据当前语言显示相应的格式
      if (el.type === 'date' || el.type === 'datetime-local') {
        // 使用当前语言的翻译
        let placeholderText = dict[key];
        // 如果没有找到，使用默认值
        if (!placeholderText) {
          if (key === 'dateFormatHint') {
            placeholderText = dict.dateFormatHint || '年/月/日';
          } else if (key === 'dateTimeFormatHint') {
            placeholderText = dict.dateTimeFormatHint || '年/月/日';
          } else if (key === 'mcFromPlaceholder' || key === 'mcToPlaceholder') {
            placeholderText = dict[key] || dict.dateTimeFormatHint || '年/月/日';
          } else {
            placeholderText = dict[key] || '';
          }
        }
        if (placeholderText) {
          el.setAttribute('placeholder', placeholderText);
        }
      } else {
        if (dict[key]) {
          el.setAttribute('placeholder', dict[key]);
        }
      }
    });

    const titleEl = document.querySelector('title[data-i18n]');
    if (titleEl) {
      const key = titleEl.dataset.i18n;
      if (dict[key]) {
        titleEl.textContent = dict[key];
        document.title = dict[key];
      }
    }

    document.querySelectorAll('[data-current-lang-label]').forEach(el => {
      el.textContent = dict.langLabel;
    });

    enhanceLocalizedDatetimeInputs();
  }

  function bindLanguageSwitcher() {
    document.querySelectorAll('.lang-option').forEach(option => {
      option.addEventListener('click', event => {
        event.preventDefault();
        const lang = resolveLanguage(option.dataset.lang);
        setLanguage(lang);
      });
    });
  }

  function setLanguage(lang) {
    currentLang = lang;
    localStorage.setItem(STORAGE_KEY, lang);
    applyTranslations(lang);
    // 重新应用日期时间输入框的占位符，确保格式统一
    enhanceLocalizedDatetimeInputs();
    window.dispatchEvent(new CustomEvent('language:changed', { detail: { lang } }));
  }

  function enhanceLocalizedDatetimeInputs() {
    const inputs = document.querySelectorAll('input[type="date"], input[type="datetime-local"]');
    inputs.forEach(input => {
      if (input.dataset.datetimeEnhanceSkip === 'true') return;
      const originalType = input.dataset.datetimeType || input.getAttribute('type') || 'datetime-local';
      input.dataset.datetimeType = originalType;

      // 统一使用中文格式的占位符
      const placeholderKey = input.dataset.i18nPlaceholder ||
        (originalType === 'date' ? 'dateFormatHint' : 'dateTimeFormatHint');
      input.dataset.i18nPlaceholder = placeholderKey;

      const applyPlaceholder = () => {
        if (input.dataset.datetimeState === 'active') return;
        // 根据当前语言显示相应的日期格式
        const currentDict = translations[currentLang] || translations.zh;
        let text = currentDict[placeholderKey] || translateKey(placeholderKey);

        // 如果没有找到对应的翻译，使用默认值
        if (!text || text === placeholderKey) {
          if (placeholderKey === 'dateFormatHint') {
            text = currentDict.dateFormatHint || '年/月/日';
          } else if (placeholderKey === 'dateTimeFormatHint') {
            text = currentDict.dateTimeFormatHint || '年/月/日';
          } else if (placeholderKey === 'mcFromPlaceholder' || placeholderKey === 'mcToPlaceholder') {
            text = currentDict[placeholderKey] || currentDict.dateTimeFormatHint || '年/月/日';
          } else {
            text = currentDict[placeholderKey] || text;
          }
        }

        if (!input.value) {
          input.setAttribute('placeholder', text);
        } else {
          input.removeAttribute('placeholder');
        }
      };

      // 创建或获取覆盖层元素（用于显示统一的格式提示）
      let overlaySpan = input.parentElement.querySelector('.datetime-overlay-helper');
      if (!overlaySpan && (originalType === 'datetime-local' || originalType === 'date')) {
        // 确保父元素是相对定位
        if (getComputedStyle(input.parentElement).position === 'static') {
          input.parentElement.style.position = 'relative';
        }
        overlaySpan = document.createElement('span');
        overlaySpan.className = 'datetime-overlay-helper';
        overlaySpan.style.cssText = 'position: absolute; left: 12px; top: 50%; transform: translateY(-50%); pointer-events: none; color: #6b7186; font-size: 0.9rem; z-index: 10; background: white; padding: 0 2px; display: none; white-space: nowrap;';
        input.parentElement.appendChild(overlaySpan);
      }

      const activate = () => {
        input.dataset.datetimeState = 'active';
        input.type = originalType;
        // 即使激活状态下，也保持统一的占位符显示，避免浏览器显示默认格式
        if (!input.value) {
          // 根据当前语言显示相应的日期格式
          const currentDict = translations[currentLang] || translations.zh;
          let placeholderText = currentDict[placeholderKey];

          // 如果没有找到对应的翻译，使用默认值
          if (!placeholderText) {
            if (placeholderKey === 'dateFormatHint') {
              placeholderText = currentDict.dateFormatHint || '年/月/日';
            } else if (placeholderKey === 'dateTimeFormatHint') {
              placeholderText = currentDict.dateTimeFormatHint || '年/月/日';
            } else if (placeholderKey === 'mcFromPlaceholder' || placeholderKey === 'mcToPlaceholder') {
              placeholderText = currentDict[placeholderKey] || currentDict.dateTimeFormatHint || '年/月/日';
            } else {
              placeholderText = currentDict[placeholderKey] || '年/月/日';
            }
          }

          input.setAttribute('placeholder', placeholderText);
          // 显示覆盖层
          if (overlaySpan) {
            overlaySpan.textContent = placeholderText;
            overlaySpan.style.display = '';
            // 隐藏输入框的默认文本显示
            input.style.color = 'transparent';
            input.style.caretColor = 'var(--primary, #5b7cfa)';
          }
        } else {
          input.removeAttribute('placeholder');
          if (overlaySpan) {
            overlaySpan.style.display = 'none';
            input.style.color = '';
          }
        }
      };

      const deactivate = () => {
        if (input.value) return;
        input.dataset.datetimeState = 'idle';
        input.type = 'text';
        // 隐藏覆盖层
        if (overlaySpan) {
          overlaySpan.style.display = 'none';
          input.style.color = '';
        }
        applyPlaceholder();
      };

      if (!input.dataset.datetimeEnhanced) {
        input.addEventListener('focus', () => {
          activate();
          if (typeof input.showPicker === 'function') {
            setTimeout(() => {
              try { input.showPicker(); } catch (err) { /* ignore */ }
            }, 0);
          }
        });

        input.addEventListener('blur', () => {
          if (!input.value) {
            deactivate();
          } else {
            // 有值时隐藏覆盖层
            if (overlaySpan) {
              overlaySpan.style.display = 'none';
              input.style.color = '';
            }
          }
        });

        input.addEventListener('change', () => {
          if (!input.value) {
            deactivate();
          } else {
            // 有值时隐藏覆盖层
            if (overlaySpan) {
              overlaySpan.style.display = 'none';
              input.style.color = '';
            }
          }
        });

        input.addEventListener('input', () => {
          // 当用户输入时，隐藏覆盖层
          if (input.value && overlaySpan) {
            overlaySpan.style.display = 'none';
            input.style.color = '';
          } else if (!input.value && overlaySpan && input.dataset.datetimeState === 'active') {
            // 如果清空了值且处于激活状态，显示覆盖层
            activate();
          }
        });

        input.dataset.datetimeEnhanced = 'true';
      }

      // 初始化时应用占位符
      if (input.value) {
        activate();
      } else {
        deactivate();
      }

      // 监听语言切换事件，更新占位符
      window.addEventListener('language:changed', () => {
        if (input.dataset.datetimeState === 'active' && !input.value) {
          activate();
        } else if (input.dataset.datetimeState === 'idle') {
          applyPlaceholder();
        }
      });
    });
  }

  document.addEventListener('DOMContentLoaded', () => {
    currentLang = resolveLanguage(currentLang);
    setLanguage(currentLang);
    enhanceLocalizedDatetimeInputs();
    bindLanguageSwitcher();
  });

  window.LanguageManager = {
    setLanguage,
    getCurrent: () => currentLang
  };
  window.t = translateKey;
})();

