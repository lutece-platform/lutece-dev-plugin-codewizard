/*
 * Copyright (c) 2002-2016, Mairie de Paris
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
package fr.paris.lutece.plugins.codewizard.service;

import fr.paris.lutece.plugins.codewizard.business.BusinessObject;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.web.l10n.LocaleService;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.html.HtmlTemplate;

import java.util.HashMap;



/**
 * This class generatot class
 */
public class GeneratorService
{
    //Constants
    private static final String PROPERTY_GENERATOR = "codewizard.generator";
    private static final String MARK_OBJECT_BO = "businessObject";

    /** private constructor */
    private GeneratorService()
    {
    }

    /**
     * Generate code
     * @param bo The business object
     * @param nIndex The template index
     * @return The source code
     */
    public static String generate( BusinessObject bo, int nIndex )
    {
        String strTemplate = getTemplate( nIndex );
        HashMap model = new HashMap(  );
        model.put( MARK_OBJECT_BO, bo );

        HtmlTemplate template = AppTemplateService.getTemplate( strTemplate, LocaleService.getDefault(), model );

        return template.getHtml(  );
    }
    
    /**
     * Return the list of generators
     *
     * @return the list of generators
     */
    public static ReferenceList getGeneratorsList(  )
    {
        ReferenceList listGenerators = new ReferenceList(  );
        String strGeneratorText;
        int i = 1;

        while ( ( strGeneratorText = AppPropertiesService.getProperty( PROPERTY_GENERATOR + i + ".text" ) ) != null )
        {
            listGenerators.addItem( i, strGeneratorText );
            i++;
        }

        return listGenerators;
    }

    /**
     * Return template of generation
     * @param nIndex the index
     * @return the template
     */
    private static String getTemplate( int nIndex )
    {
        String strTemplate = AppPropertiesService.getProperty( PROPERTY_GENERATOR + nIndex + ".template" );

        return strTemplate;
    }    

}
