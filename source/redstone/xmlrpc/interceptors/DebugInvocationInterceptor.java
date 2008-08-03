/*
    Copyright (c) 2007 Redstone Handelsbolag

    This library is free software; you can redistribute it and/or modify it under the terms
    of the GNU Lesser General Public License as published by the Free Software Foundation;
    either version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
    without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
    See the GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License along with this
    library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
    Boston, MA  02111-1307  USA
*/

package redstone.xmlrpc.interceptors;

import javax.servlet.ServletContext;
import redstone.xmlrpc.XmlRpcInvocation;
import redstone.xmlrpc.XmlRpcInvocationInterceptor;

/**
 *  Simple invocation processor that traces the calls made through an XmlRpcServer.
 *  This is used for debugging purposes only. This may be replaced with a more
 *  competent logging processor that perhaps is only logging exceptions that occur.<p>
 *  
 *  Logging occurs either on System.out or on the servlet container log depending
 *  on if a ServletContext is supplied or not when constructing the interceptor.
 *
 *  @author Greger Olsson
 */

public class DebugInvocationInterceptor implements XmlRpcInvocationInterceptor
{
    /**
     *  Empty default constructor. Using this constructor rather than the
     *  one accepting a ServletContext will cause the output to be printed
     *  on System.out instead of the servlet container log.
     */

    public DebugInvocationInterceptor()
    {
    }


    /**
     *  Constructs the interceptor while associating it with the supplied
     *  servlet context. Output will be directed to the servlet container log.
     *  
     *  @param servletContext The servlet context to be used for logging.
     */
    
    public DebugInvocationInterceptor( ServletContext servletContext )
    {
        this.servletContext = servletContext;
    }

    
    /**
     *  Outputs information bout the invocation, the method, and its arguments.
     *  
     *  @param invocation The invocation.
     */

    public boolean before( XmlRpcInvocation invocation )
    {
        StringBuffer message = new StringBuffer( 192 );

        message.append( invocation.getInvocationId() )
               .append( ": " )
               .append( invocation.getHandlerName() )
               .append( '.' )
               .append( invocation.getMethodName() )
               .append( invocation.getArguments().toString() );
        
        if ( servletContext != null )
        {
            servletContext.log( message.toString() );
        }
        else
        {
            System.out.println( message.toString() );
        }
        
        return true;
    }


    /**
     *  Prints trace info on the invocation return value.
     *  
     *  @param invocation The invocation.
     *  @param returnValue The value returned from the method.
     */

    public Object after( XmlRpcInvocation invocation, Object returnValue )
    {
        StringBuffer message = new StringBuffer( 192 );

        message.append( invocation.getInvocationId() )
               .append( ": " )
               .append( returnValue );
        
        if ( servletContext != null )
        {
            servletContext.log( message.toString() );
        }
        else
        {
            System.out.println( message.toString() );
        }

        return returnValue;
    }


    /**
     *  Prints trace info on the invocation exception.
     *
     *  @param invocation The invocation.
     *  @param exception The exception thrown by the method.
     */

    public void onException( XmlRpcInvocation invocation, Throwable exception )
    {
        StringBuffer message = new StringBuffer( 192 );

        message.append( invocation.getInvocationId() )
               .append( ": " )
               .append( exception.getMessage() );
        
        if ( servletContext != null )
        {
            servletContext.log( message.toString(), exception );
        }
        else
        {
            if ( exception.getCause() != null )
            {
                message.append( exception.getCause().getMessage() );
            }
            
            System.out.println( message.toString() );

        }
    }
    
    
    /** The servlet context that logging will be performed over. */
    private ServletContext servletContext;
}
