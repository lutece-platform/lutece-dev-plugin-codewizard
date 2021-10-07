/*
 * Copyright (c) 2002-2017, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.codewizard.web;

import fr.paris.lutece.plugins.codewizard.business.BusinessObject;
import fr.paris.lutece.plugins.codewizard.business.ObjectAttribute;
import fr.paris.lutece.plugins.codewizard.service.GeneratorService;
import fr.paris.lutece.plugins.codewizard.service.JavaTypeService;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.portal.web.xpages.XPageApplication;
import fr.paris.lutece.util.html.HtmlTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

/**
 * Thi class manage CodeWizard Page
 */
public class CodeWizardApp implements XPageApplication
{
    /**
     * 
     */
    private static final long serialVersionUID = 2220490166227831286L;
    // Constants
    private static final String TEMPLATE_CODE_WIZARD = "/skin/plugins/codewizard/code_wizard.html";
    private static final String TEMPLATE_SOURCE_CODE = "/skin/plugins/codewizard/code_source.html";

    private static final String MARK_COMBO_GENERATORS = "combo_generators";
    private static final String MARK_SOURCE_CODE = "source_code";

    private static final String PARAM_ACTION = "action";
    private static final String PARAM_CLASSNAME = "class";
    private static final String PARAM_PACKAGE = "package";
    private static final String PARAM_TABLE = "table";
    private static final String PARAM_PLUGIN = "plugin";
    private static final String PARAM_GENERATION_TYPE = "generation_type";
    private static final String PARAM_ATTRIBUTES = "attributes";

    private static final String ACTION_GENERATE = "generate";

    private static final String PROPERTY_PAGE_TITLE = "codewizard.pageTitle";
    private static final String PROPERTY_PAGE_PATH_LABEL = "codewizard.pagePathLabel";

    /**
     * getPage
     *
     * @param request
     *            HttpServletRequest
     * @param nMode
     *            int
     * @param plugin
     *            Plugin
     * @return XPage
     */
    @Override
    public XPage getPage( HttpServletRequest request, int nMode, Plugin plugin )
    {
        XPage page = new XPage( );

        page.setTitle( I18nService.getLocalizedString( PROPERTY_PAGE_TITLE, request.getLocale( ) ) );
        page.setPathLabel( I18nService.getLocalizedString( PROPERTY_PAGE_PATH_LABEL, request.getLocale( ) ) );

        String strContent;
        String strAction = request.getParameter( PARAM_ACTION );

        if ( ( strAction != null ) && ( strAction.equals( ACTION_GENERATE ) ) )
        {
            strContent = getGeneratePage( request );
        }
        else
        {
            strContent = getCodeWizardPage( request );
        }

        page.setContent( strContent );

        return page;
    }

    /**
     * Return the CodeWizard Page
     * 
     * @param request
     *            The HttpServletRequest
     * @return the html code
     */
    private String getCodeWizardPage( HttpServletRequest request )
    {
        Map<String, Object> model = new HashMap<>( );
        model.put( MARK_COMBO_GENERATORS, GeneratorService.getGeneratorsList( ) );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_CODE_WIZARD, request.getLocale( ), model );

        return template.getHtml( );
    }

    /**
     * Return the generated page
     * 
     * @param request
     *            The HttpServletRequest
     * @return strPage
     */
    private String getGeneratePage( HttpServletRequest request )
    {
        String strSourceCode = "";
        String strPackageName = request.getParameter( PARAM_PACKAGE );
        String strClassName = request.getParameter( PARAM_CLASSNAME );
        String strTable = request.getParameter( PARAM_TABLE );
        String strPlugin = request.getParameter( PARAM_PLUGIN );
        String strGenerationType = request.getParameter( PARAM_GENERATION_TYPE );
        String strAttributesList = request.getParameter( PARAM_ATTRIBUTES );
        BusinessObject bo = new BusinessObject( );

        bo.setClassName( strClassName );
        bo.setPackageName( strPackageName );
        bo.setTable( strTable );
        bo.setPluginName( strPlugin );
        parseAttributesList( strAttributesList, bo );

        if ( strGenerationType != null )
        {
            int nIndex = Integer.parseInt( strGenerationType );
            strSourceCode = GeneratorService.generate( bo, nIndex );
        }

        Map<String, Object> model = new HashMap<>( );
        model.put( MARK_SOURCE_CODE, strSourceCode );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_SOURCE_CODE, request.getLocale( ), model );

        return template.getHtml( );
    }

    /**
     * Parse the attributes of the list
     * 
     * @param strAttributesList
     *            the list of attributes
     * @param bo
     *            the BusinessObject
     */
    private void parseAttributesList( String strAttributesList, BusinessObject bo )
    {
        StringTokenizer stLines = new StringTokenizer( strAttributesList.trim( ), "\r\n" );
        boolean bFirst = true;

        while ( stLines.hasMoreTokens( ) )
        {
            String strLine = stLines.nextToken( );
            strLine = strLine.trim( );

            String[] tokens = strLine.split( "\\s+" );

            if ( tokens.length >= 2 )
            {
                String strColumnName = tokens[0];
                String strJavaType = tokens[1];
                ObjectAttribute attribute = JavaTypeService.getAttribute( strColumnName, strJavaType );
                bo.addAttribute( attribute );
                if( tokens.length >= 3 )
                {
                    attribute.setInputType( tokens[2] );
                }

                if ( bFirst )
                {
                    bo.setIdColumnName( strColumnName );
                    bFirst = false;
                }
            }
        }
    }

}
