<div class="page-sidebar-wrapper">
    <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
    <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
    <div class="page-sidebar navbar-collapse collapse">
        <!-- BEGIN SIDEBAR MENU -->
        <ul class="page-sidebar-menu" data-auto-scroll="true" data-slide-speed="200">
        	<li class="sidebar-toggler-wrapper">
				<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				<div class="sidebar-toggler">
				</div>
				<!-- END SIDEBAR TOGGLER BUTTON -->
			</li>
            <!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
	            <li class="start [#if pattern == "home"]active [/#if] ">
	                <a href="${base}/admin/common/main.jhtml">
	                <i class="icon-home"></i>
	                <span class="title">${message("admin.index.title")}</span>
	                [#if pattern == "home"]<span class="selected"></span> [/#if]
	                </a>
	            </li>
           [#assign contentGroup = ["admin:email","admin:group","admin:user","admin:button","admin:template","admin:request","admin:qrcode","admin:map"] ]
            [#list contentGroup as permission]
				[@shiro.hasPermission name = permission]
		            <li [#if contentGroup?seq_contains(pattern) ]class="active open"[/#if]>
		                <a href="javascript:;">
			                <i class="icon-book-open"></i>  <span class="title">  ${message("admin.main.contentGroup")} </span>
			                [#if contentGroup?seq_contains(pattern) ]<span class="selected"></span>[/#if]
			                <span class="arrow [#if contentGroup?seq_contains(pattern) ]open[/#if]"> </span>
		                </a>
		                <ul class="sub-menu" style=" [#if contentGroup?seq_contains(pattern) ]display:block;[#else]display:none;[/#if]">
		                    [@shiro.hasPermission name="admin:email"]
			                    <li class="tooltips [#if pattern == "admin:email"]active[/#if]" data-container="body" data-placement="right" data-html="true" data-original-title="">
			                        <a href="${base}/admin/email/list.jhtml">
			                        	<span class="title">${message("admin.main.email")} </span>
			                        </a>
			                    </li>
		                    [/@shiro.hasPermission]
		                    [#if setting.isWechatEnabled!=false]
		                    [@shiro.hasPermission name="admin:group"]
			                    <li class="tooltips [#if pattern == "admin:group"]active[/#if]" data-container="body" data-placement="right" data-html="true" data-original-title="">
			                        <a href="${base}/admin/group/list.jhtml">
			                        	<span class="title">${message("admin.common.group")} </span>
			                        </a>
			                    </li>
		                    [/@shiro.hasPermission]
		                    [@shiro.hasPermission name="admin:user"]
			                    <li class="tooltips [#if pattern == "admin:user"]active[/#if]" data-container="body" data-placement="right" data-html="true" data-original-title="">
			                        <a href="${base}/admin/user/list.jhtml">
			                        	<span class="title">${message("admin.common.member")} </span>
			                        </a>
			                    </li>
		                    [/@shiro.hasPermission]
		                    [@shiro.hasPermission name="admin:button"]
			                    <li class="tooltips [#if pattern == "admin:button"]active[/#if]" data-container="body" data-placement="right" data-html="true" data-original-title="">
			                        <a href="${base}/admin/button/list.jhtml">
			                        	<span class="title">${message("admin.common.button")} </span>
			                        </a>
			                    </li>
		                    [/@shiro.hasPermission]
		                    [@shiro.hasPermission name="admin:request"]
			                    <li class="tooltips [#if pattern == "admin:request"]active[/#if]" data-container="body" data-placement="right" data-html="true" data-original-title="">
			                        <a href="${base}/admin/request/list.jhtml">
			                        	<span class="title">${message("admin.common.request")} </span>
			                        </a>
			                    </li>
		                    [/@shiro.hasPermission]
		                    [/#if]
		                    [@shiro.hasPermission name="admin:template"]
			                    <li class="tooltips [#if pattern == "admin:template"]active[/#if]" data-container="body" data-placement="right" data-html="true" data-original-title="">
			                        <a href="${base}/admin/template/list.jhtml">
			                        	<span class="title">${message("admin.common.template")} </span>
			                        </a>
			                    </li>
		                    [/@shiro.hasPermission]
		                    
		                    
		                    [@shiro.hasPermission name="admin:qrcode"]
			                    <li class="tooltips [#if pattern == "admin:qrcode"]active[/#if]" data-container="body" data-placement="right" data-html="true" data-original-title="">
			                        <a href="${base}/admin/qrcode.jhtml">
			                        	<span class="title">${message("admin.common.qrcode")}</span>
			                        </a>
			                    </li>
		                    [/@shiro.hasPermission]
		                    
		                    [@shiro.hasPermission name="admin:map"]
			                    <li class="tooltips [#if pattern == "admin:map"]active[/#if]" data-container="body" data-placement="right" data-html="true" data-original-title="">
			                        <a href="${base}/admin/map.jhtml">
			                        	<span class="title">${message("admin.common.map")} </span>
			                        </a>
			                    </li>
		                    [/@shiro.hasPermission]
		                    
		                </ul>
		            </li>
		            [#break /]
				[/@shiro.hasPermission]
			[/#list]
            [#assign systemGroup = ["admin:setting","admin:storagePlugin", "admin:paymentPlugin","admin:admin","admin:role","admin:log","admin:druid"] ]
            [#list systemGroup as permission]
				[@shiro.hasPermission name = permission]
		            <li [#if systemGroup?seq_contains(pattern) ]class="active open"[/#if]>
		                <a href="javascript:;">
			                <i class="icon-settings"></i>  <span class="title">  ${message("admin.main.systemGroup")} </span>
			                [#if systemGroup?seq_contains(pattern) ]<span class="selected"></span>[/#if]
			                <span class="arrow [#if systemGroup?seq_contains(pattern) ]open[/#if]"> </span>
		                </a>
		                <ul class="sub-menu" style=" [#if systemGroup?seq_contains(pattern) ]display:block;[#else]display:none;[/#if]">
		                    [@shiro.hasPermission name="admin:setting"]
			                    <li class="tooltips [#if pattern == "admin:setting"]active[/#if]" data-container="body" data-placement="right" data-html="true" data-original-title="">
			                        <a href="${base}/admin/setting/edit.jhtml">
			                        	<span class="title">${message("admin.main.setting")} </span>
			                        </a>
			                    </li>
		                    [/@shiro.hasPermission]
		                    [@shiro.hasPermission name="admin:storagePlugin"]
		                    	 <li class="tooltips [#if pattern == "admin:storagePlugin"]active[/#if]" data-container="body" data-placement="right" data-html="true" data-original-title="">
			                        <a href="${base}/admin/storage_plugin/list.jhtml">
			                        	<span class="title">${message("admin.main.storagePlugin")} </span>
			                        </a>
			                    </li>
							[/@shiro.hasPermission]
							[@shiro.hasPermission name="admin:paymentPlugin"]
								<li class="tooltips [#if pattern == "admin:paymentPlugin"]active[/#if]" data-container="body" data-placement="right" data-html="true" data-original-title="">
			                        <a href="${base}/admin/payment_plugin/list.jhtml">
			                        	<span class="title">${message("admin.main.paymentPlugin")} </span>
			                        </a>
			                    </li>
							[/@shiro.hasPermission]
		                    [@shiro.hasPermission name="admin:admin"]
			                    <li class="tooltips [#if pattern == "admin:admin"]active[/#if]" data-container="body" data-placement="right" data-html="true" data-original-title="">
			                        <a href="${base}/admin/admin/list.jhtml">
			                        	<span class="title">${message("admin.main.admin")} </span>
			                        </a>
			                    </li>
		                    [/@shiro.hasPermission]
		                    [@shiro.hasPermission name="admin:role"]
		                    	<li class="tooltips [#if pattern == "admin:role"]active[/#if]" data-container="body" data-placement="right" data-html="true" data-original-title="">
			                        <a href="${base}/admin/role/list.jhtml">
			                        	<span class="title">${message("admin.main.role")} </span>
			                        </a>
			                    </li>
							[/@shiro.hasPermission]
							[@shiro.hasPermission name="admin:log"]
								<li class="tooltips [#if pattern == "admin:log"]active[/#if]" data-container="body" data-placement="right" data-html="true" data-original-title="">
			                        <a href="${base}/admin/log/list.jhtml">
			                        	<span class="title">${message("admin.main.log")} </span>
			                        </a>
			                    </li>
							[/@shiro.hasPermission]
							   [@shiro.hasPermission name="admin:druid"]
			                    <li class="tooltips [#if pattern == "admin:druid"]active[/#if]" data-container="body" data-placement="right" data-html="true" data-original-title="">
			                        <a href="${base}/admin/druid.jhtml">
			                        	<span class="title">${message("Datasource.druid")} </span>
			                        </a>
			                    </li>
		                    [/@shiro.hasPermission]
		                </ul>
		            </li>
		            [#break /]
				[/@shiro.hasPermission]
			[/#list]
        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
</div>