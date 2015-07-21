<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${base}/resources/plugin/metronic/global/plugins/respond.min.js"></script>
<script src="${base}/resources/plugin/metronic/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="${base}/resources/plugin/metronic/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${base}/resources/plugin/metronic/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${base}/resources/plugin/metronic/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
<script src="${base}/resources/plugin/metronic/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/resources/plugin/metronic/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${base}/resources/plugin/metronic/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${base}/resources/plugin/metronic/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${base}/resources/plugin/metronic/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${base}/resources/plugin/metronic/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${base}/resources/plugin/metronic/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<script src="${base}/resources/plugin/metronic/global/plugins/morris/morris.min.js" type="text/javascript"></script>
<script src="${base}/resources/plugin/metronic/global/plugins/morris/raphael-min.js" type="text/javascript"></script>
<script src="${base}/resources/plugin/metronic/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->

<script type="text/javascript" src="${base}/resources/plugin/metronic/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${base}/resources/plugin/metronic/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script type="text/javascript" src="${base}/resources/plugin/metronic/global/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>


<script type="text/javascript" src="${base}/resources/plugin/metronic/global/plugins/jquery-validation/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${base}/resources/plugin/metronic/global/plugins/jquery-validation/js/additional-methods.min.js"></script>

<script type="text/javascript" src="${base}/resources/plugin/metronic/global/plugins/bootstrap-select/bootstrap-select.min.js"></script>
<script type="text/javascript" src="${base}/resources/plugin/metronic/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${base}/resources/plugin/metronic/global/plugins/jquery-multi-select/js/jquery.multi-select.js"></script>
<script type="text/javascript" src="${base}/resources/plugin/metronic/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${base}/resources/plugin/artdialog/artDialog.min.js?skin=twitter" type="text/javascript"></script>
<script src="${base}/resources/plugin/artdialog/artDialog.plugins.js" type="text/javascript"></script>
<script src="${base}/resources/plugin/kino/kino.razor.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->


<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${base}/resources/plugin/metronic/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${base}/resources/plugin/metronic/admin/layout4/scripts/layout.js" type="text/javascript"></script>
<script src="${base}/resources/plugin/metronic/admin/layout4/scripts/demo.js" type="text/javascript"></script>
<script src="${base}/resources/plugin/metronic/admin/pages/scripts/index3.js" type="text/javascript"></script>
<script src="${base}/resources/plugin/metronic/admin/pages/scripts/tasks.js" type="text/javascript"></script>
<script src="${base}/resources/plugin/metronic/global/plugins/jquery.pulsate.min.js" type="text/javascript"></script>

<!-- END PAGE LEVEL PLUGINS -->

<script type="text/javascript">
	jQuery(document).ready(function() {    
	   	Metronic.init(); // init metronic core componets
   		Layout.init(); // init layout
   		Demo.init(); // init demo features 
    	//Index.init(); // init index page
 		Tasks.initDashboardWidget(); // init tash dashboard widget  
	});
</script>
