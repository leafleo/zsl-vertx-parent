<head>
	<meta charset="utf-8"/>
	<title>${setting.siteName} - ${message("admin.index.title")}</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1" name="viewport"/>
	<meta content="" name="description"/>
	<meta content="" name="author"/>
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	[#if locale != "zh_CN"]
		<link href="http://fonts.useso.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
	[/#if]
	<script src="${base}/resources/plugin/metronic/global/plugins/pace/pace.min.js" type="text/javascript"></script>
	<link href="${base}/resources/plugin/metronic/global/plugins/pace/themes/pace-theme-flash.css" rel="stylesheet" type="text/css"/>
	
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="${base}/resources/plugin/metronic/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/resources/plugin/metronic/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/resources/plugin/metronic/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/resources/plugin/metronic/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/resources/plugin/metronic/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
	<link href="${base}/resources/plugin/metronic/global/plugins/morris/morris.css" rel="stylesheet" type="text/css">
	<!-- END PAGE LEVEL PLUGIN STYLES -->
	<!-- BEGIN PAGE STYLES -->
	<link href="${base}/resources/plugin/metronic/admin/pages/css/tasks.css" rel="stylesheet" type="text/css"/>
	<!-- END PAGE STYLES -->
	<link rel="stylesheet" type="text/css" href="${base}/resources/plugin/metronic/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
	<link rel="stylesheet" type="text/css" href="${base}/resources/plugin/metronic/global/plugins/select2/select2.css"/>
	<link rel="stylesheet" type="text/css" href="${base}/resources/plugin/metronic/global/plugins/jquery-multi-select/css/multi-select.css"/>
	
	<link rel="stylesheet" type="text/css" href="${base}/resources/plugin/metronic/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"/>
	<link rel="stylesheet" type="text/css" href="${base}/resources/plugin/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>
	<link rel="stylesheet" type="text/css" href="${base}/resources/plugin/metronic/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"/>
	
	
	<link href="${base}/resources/plugin/artdialog/skins/default.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css"  />
	<!-- BEGIN THEME STYLES -->
	[@current]
	<!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
	<link href="${base}/resources/plugin/metronic/global/css/components[#if current.themes.themeStyle.lowerName()=="rounded"]-rounded[/#if].css" id="style_components" rel="stylesheet" type="text/css"/>
	<link href="${base}/resources/plugin/metronic/global/css/plugins.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/resources/plugin/metronic/admin/layout4/css/layout.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/resources/plugin/metronic/admin/layout4/css/themes/${(current.themes.themeColor.lowerName())!'light'}.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="${base}/resources/plugin/metronic/admin/layout4/css/custom.css" rel="stylesheet" type="text/css"/>
	<!-- END THEME STYLES -->
	[/@current]
	
	[#if locale == "zh_CN"]
		<style>
			html, body, div, span, input, p,h1, h2, h3, h4, h5, h6, blockquote, a, abbr, acronym, address, strong, b, u, i, center,dl, dt, dd, ol, ul,td,th,tr {
			    font-family:"Microsoft Yahei","微软雅黑","宋体", "Simsun", "Open Sans";
			}
		</style>
	[/#if]
</head>