<!-- BEGIN PAGE TOOLBAR -->
[@current]
<form id="themesForm">
<div class="page-toolbar">
	<!-- BEGIN THEME PANEL -->
	<div class="btn-group btn-theme-panel">
		<a href="javascript:;" class="btn dropdown-toggle" data-toggle="dropdown">
		<i class="icon-settings"></i>
		</a>
		<div class="dropdown-menu theme-panel pull-right dropdown-custom hold-on-click">
			<div class="row">
				<div class="col-md-4 col-sm-4 col-xs-12">
					<h3>选择主题颜色</h3>
					<ul class="theme-colors">
						<li class="theme-color theme-color-default [#if current.themes.themeColor??][#if current.themes.themeColor.lowerName() == "default"]active[/#if][#else]active[/#if]" data-theme="default">
							<span class="theme-color-view" ></span>
							<span class="theme-color-name">深色头部</span>
						</li>
						<li class="theme-color theme-color-light [#if current.themes.themeColor??][#if current.themes.themeColor.lowerName() == "light"]active[/#if][#else]active[/#if]" data-theme="light">
							<span class="theme-color-view"></span>
							<span class="theme-color-name">浅色头部</span>
						</li>
					</ul>
				</div>
				<input type="hidden" value="${current.themes.themeColor}" name="themeColor" id="themeColor"/>
				<div class="col-md-8 col-sm-8 col-xs-12 seperator">
					<h3>布局</h3>
					<ul class="theme-settings">
						<li>
							 主题样式
							<select class="layout-style-option form-control input-small input-sm" name="themeStyle">
								<option value="square" [#if current.themes.themeStyle.lowerName() == "square"]selected="selected"[/#if]>方角</option>
								<option value="rounded"  [#if current.themes.layout??][#if current.themes.themeStyle.lowerName() == "rounded"]selected="selected"[/#if][#else]selected="selected"[/#if]>圆角</option>
							</select>
						</li>
						<li>
							 布局
							 <select class="layout-option form-control input-small" name="layout">
								<option value="fluid" [#if current.themes.layout??][#if current.themes.layout.lowerName() == "fluid"]selected="selected"[/#if][#else]selected="selected"[/#if]>流体</option>
								<option value="boxed" [#if current.themes.layout.lowerName() == "boxed"]selected="selected"[/#if]>盒装</option>
							</select>
						</li>
						<li>
							头部 
							<select class="page-header-option form-control input-small" name="header">
								<option value="fixed" [#if current.themes.header??][#if current.themes.header.lowerName() == "fixed"]selected="selected"[/#if][#else]selected="selected"[/#if]>固定</option>
								<option value="default" [#if current.themes.header.lowerName() == "default"]selected="selected"[/#if]>默认</option>
							</select>
						</li>
						<li>
							顶部下拉
							<select class="page-header-top-dropdown-style-option form-control input-small input-sm" name="dropdowns">
								<option value="light" [#if current.themes.dropdowns.lowerName() == "light"]selected="selected"[/#if]>浅色</option>
								<option value="dark"[#if current.themes.header??][#if current.themes.dropdowns.lowerName() == "dark"]selected="selected"[/#if][#else]selected="selected"[/#if]>深色</option>
							</select>
						</li>
						<li>
							侧边栏模式
							<select class="sidebar-option form-control input-small" name="siderbarMode">
								<option value="fixed" [#if current.themes.siderbarMode.lowerName() == "fixed"]selected="selected"[/#if]>固定</option>
								<option value="default" [#if current.themes.siderbarMode??][#if current.themes.siderbarMode.lowerName() == "default"]selected="selected"[/#if][#else]selected="selected"[/#if]>默认</option>
							</select>
						</li>
						<li>
							侧边栏菜单
							<select class="sidebar-menu-option form-control input-small" name="siderbarMenu">
								<option value="accordion" [#if current.themes.siderbarMenu??][#if current.themes.siderbarMenu.lowerName() == "accordion"]selected="selected"[/#if][#else]selected="selected"[/#if]>手风琴</option>
								<option value="hover" [#if current.themes.siderbarMenu.lowerName() == "hover"]selected="selected"[/#if]>悬停</option>
							</select>
						</li>
						<li>
							侧边栏位置
							<select class="sidebar-pos-option form-control input-small" name="siderbarPosition">
								<option value="left"  [#if current.themes.siderbarPosition??][#if current.themes.siderbarPosition.lowerName() == "left"]selected="selected"[/#if][#else]selected="selected"[/#if]>左侧</option>
								<option value="right" [#if current.themes.siderbarPosition.lowerName() == "right"]selected="selected"[/#if] >右侧</option>
							</select>
						</li>
						<li>
							 底部 
							<select class="page-footer-option form-control input-small" name="footer">
								<option value="fixed" [#if current.themes.footer.lowerName() == "fixed"]selected="selected"[/#if] >固定</option>
								<option value="default"  [#if current.themes.footer??][#if current.themes.footer.lowerName() == "default"]selected="selected"[/#if][#else]selected="selected"[/#if]>默认</option>
							</select>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- END THEME PANEL -->
</div>
</form>
[/@current]
<!-- END PAGE TOOLBAR -->
