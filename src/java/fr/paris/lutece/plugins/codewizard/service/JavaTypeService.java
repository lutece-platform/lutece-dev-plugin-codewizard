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

package fr.paris.lutece.plugins.codewizard.service;

import fr.paris.lutece.plugins.codewizard.business.JavaType;
import fr.paris.lutece.plugins.codewizard.business.ObjectAttribute;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * JavaTypeService
 */
public class JavaTypeService
{
    private static Map<String, JavaType> _mapJavaType = new HashMap<>( );
    private static boolean _bInit;

    private static void init( )
    {
        // constructor parameters : Java type | prefix | use in DAO | required import | test value format
        _mapJavaType.put( "int", new JavaType( "int", "n", true , null , "{0}" ) );
        _mapJavaType.put( "float", new JavaType( "float", "f", true  , null , "{0}.0") );
        _mapJavaType.put( "long", new JavaType( "long", "l", true , null , "{0}L" ) );
        _mapJavaType.put( "double", new JavaType( "double", "d", true  , null , "{0}.0") );
        _mapJavaType.put( "boolean", new JavaType( "boolean", "b", true , null , "true" ) );
        _mapJavaType.put( "string", new JavaType( "String", "str", true , null , "\"{1}{0}\"" ) );
        _mapJavaType.put( "date", new JavaType( "Date", "date", true , "java.sql.Date" , "new Date( {0}L )" ) );
        _mapJavaType.put( "timestamp", new JavaType( "Timestamp", "date", true , "java.sql.Timestamp"  , "new Timestamp( {0}L )" ) );
        _mapJavaType.put( "map", new JavaType( "Map", "map", false , "java.util.Map" , "" ) );
        _mapJavaType.put( "list", new JavaType( "List", "list", false , "java.util.List" , ""  ) );
        _bInit = true;
    }

    public static ObjectAttribute getAttribute( String strColumnName, String strJavaType )
    {
        ObjectAttribute attribute = new ObjectAttribute( );
        attribute.setColumnName( strColumnName );

        if ( !_bInit )
        {
            init( );
        }
        String strName = getProperName( strColumnName );
        attribute.setName( strName );
        String strJavaTypeKey = strJavaType.toLowerCase( );
        JavaType jt = _mapJavaType.get( strJavaTypeKey );
        if ( jt != null )
        {
            attribute.setType( jt.getName( ) );
            attribute.setVariableName( jt.getPrefix( ) + strName );
            attribute.setDaoType( jt.isDaoType( ) );
            Object[] args1 = { "1" , strName };
            Object[] args2 = { "2" , strName };
            attribute.setTestInitValue1( MessageFormat.format( jt.getTestInitValueFormat(), args1));
            attribute.setTestInitValue2( MessageFormat.format( jt.getTestInitValueFormat(), args2));
        }
        else
        {
            attribute.setType( strJavaType );
            attribute.setVariableName( decapitalize( strName ) );
            attribute.setDaoType( false );
        }
        return attribute;
    }

    /**
     * Return an import for a given data type if needed
     * @param strJavaType The java type
     * @return  The import or null
     */
    public static String getImport( String strJavaType )
    {
        String strImport = null;
        String strJavaTypeKey = strJavaType.toLowerCase( );
        JavaType jt = _mapJavaType.get( strJavaTypeKey );
        if( jt != null )
        {
            strImport = jt.getImport();
        }
        return strImport;
    }

    
    /**
     * Returns the Proper Name
     *
     * @param strSource
     *            the source
     * @return source
     */
    private static String getProperName( String strSource )
    {
        int nIndex = 0;
        boolean bUpper = true;
        StringBuilder sbBuffer = new StringBuilder( );

        while ( nIndex < strSource.length( ) )
        {
            char c = strSource.charAt( nIndex );

            if ( c == '_' )
            {
                // skip by reading the next char
                nIndex++;
                bUpper = true;
            }

            if ( bUpper )
            {
                String strChar = strSource.substring( nIndex, nIndex + 1 );
                c = strChar.toUpperCase( ).charAt( 0 );
                bUpper = false;
            }

            sbBuffer.append( c );
            nIndex++;
        }

        return sbBuffer.toString( );
    }

    /**
     * Set the first character to lower case
     * 
     * @param strSource
     *            The source
     * @return The new string
     */
    private static String decapitalize( String strSource )
    {
        if ( strSource == null || strSource.length( ) == 0 )
        {
            return strSource;
        }
        char c [ ] = strSource.toCharArray( );
        c [0] = Character.toLowerCase( c [0] );
        return new String( c );
    }

}
