package com.flight.trs.security.authc.credential;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月5日 下午6:32:26
 * @version V1.0
 */

/**
 * A {@code HashedCredentialMatcher} provides support for hashing of supplied {@code AuthenticationToken} credentials
 * before being compared to those in the {@code AuthenticationInfo} from the data store.
 * <p/>
 * Credential hashing is one of the most common security techniques when safeguarding a user's private credentials
 * (passwords, keys, etc).  Most developers never want to store their users' credentials in plain form, viewable by
 * anyone, so they often hash the users' credentials before they are saved in the data store.
 * <p/>
 * This class (and its subclasses) function as follows:
 * <ol>
 * <li>Hash the {@code AuthenticationToken} credentials supplied by the user during their login.</li>
 * <li>Compare this hashed value directly with the {@code AuthenticationInfo} credentials stored in the system
 * (the stored account credentials are expected to already be in hashed form).</li>
 * <li>If these two values are {@link #equals(Object, Object) equal}, the submitted credentials match, otherwise
 * they do not.</li>
 * </ol>
 * <h2>Salting and Multiple Hash Iterations</h2>
 * Because simple hashing is usually not good enough for secure applications, this class also supports 'salting'
 * and multiple hash iterations.  Please read this excellent
 * <a href="http://www.owasp.org/index.php/Hashing_Java" _target="blank">Hashing Java article</a> to learn about
 * salting and multiple iterations and why you might want to use them. (Note of sections 5
 * &quot;Why add salt?&quot; and 6 "Hardening against the attacker's attack").   We should also note here that all of
 * Shiro's Hash implementations (for example, {@link org.apache.shiro.crypto.hash.Md5Hash Md5Hash},
 * {@link org.apache.shiro.crypto.hash.Sha1Hash Sha1Hash}, etc) support salting and multiple hash iterations via
 * overloaded constructors.
 * <h4>Real World Case Study</h4>
 * In April 2010, some public Atlassian Jira and Confluence
 * installations (Apache Software Foundation, Codehaus, etc) were the target of account attacks and user accounts
 * were compromised.  The reason?  Jira and Confluence at the time did not salt user passwords and attackers were
 * able to use dictionary attacks to compromise user accounts (Atlassian has since
 * <a href="http://blogs.atlassian.com/news/2010/04/oh_man_what_a_day_an_update_on_our_security_breach.html">
 * fixed the problem</a> of course).
 * <p/>
 * The lesson?
 * <p/>
 * <b>ALWAYS, ALWAYS, ALWAYS SALT USER PASSWORDS!</b>
 * <p/>
 * <h3>Salting</h3>
 * Prior to Shiro 1.1, salts could be obtained based on the end-user submitted
 * {@link AuthenticationToken AuthenticationToken} via the now-deprecated
 * {@link #getSalt(org.apache.shiro.authc.AuthenticationToken) getSalt(AuthenticationToken)} method.  This however
 * could constitute a security hole since ideally salts should never be obtained based on what a user can submit.
 * User-submitted salt mechanisms are <em>much</em> more susceptible to dictionary attacks and <b>SHOULD NOT</b> be
 * used in secure systems.  Instead salts should ideally be a secure randomly-generated number that is generated when
 * the user account is created.  The secure number should never be disseminated to the user and always kept private
 * by the application.
 * <h4>Shiro 1.1</h4>
 * As of Shiro 1.1, it is expected that any salt used to hash the submitted credentials will be obtained from the
 * stored account information (represented as an {@link AuthenticationInfo AuthenticationInfo} instance).  This is much
 * more secure because the salt value remains private to the application (Shiro will never store this value).
 * <p/>
 * To enable this, {@code Realm}s should return {@link SaltedAuthenticationInfo SaltedAuthenticationInfo} instances
 * during authentication.  {@code HashedCredentialsMatcher} implementations will then use the provided
 * {@link org.apache.shiro.authc.SaltedAuthenticationInfo#getCredentialsSalt credentialsSalt} for hashing.  To avoid
 * security risks,
 * <b>it is highly recommended that any existing {@code Realm} implementations that support hashed credentials are
 * updated to return {@link SaltedAuthenticationInfo SaltedAuthenticationInfo} instances as soon as possible</b>.
 * <h4>Shiro 1.0 Backwards Compatibility</h4>
 * Because of the identified security risk, {@code Realm} implementations that support credentials hashing should
 * be updated to return {@link SaltedAuthenticationInfo SaltedAuthenticationInfo} instances as
 * soon as possible.
 * <p/>
 * If this is not possible for some reason, this class will retain 1.0 backwards-compatible behavior of obtaining
 * the salt via the now-deprecated {@link #getSalt(AuthenticationToken) getSalt(AuthenticationToken)} method.  This
 * method will only be invoked if a {@code Realm} <em>does not</em> return
 * {@link SaltedAuthenticationInfo SaltedAutenticationInfo} instances and {@link #isHashSalted() hashSalted} is
 * {@code true}.
 * But please note that the {@link #isHashSalted() hashSalted} property and the
 * {@link #getSalt(AuthenticationToken) getSalt(AuthenticationToken)} methods will be removed before the Shiro 2.0
 * release.
 * <h3>Multiple Hash Iterations</h3>
 * If you hash your users' credentials multiple times before persisting to the data store, you will also need to
 * set this class's {@link #setHashIterations(int) hashIterations} property.  See the
 * <a href="http://www.owasp.org/index.php/Hashing_Java" _target="blank">Hashing Java article</a>'s
 * <a href="http://www.owasp.org/index.php/Hashing_Java#Hardening_against_the_attacker.27s_attack">
 * &quot;Hardening against the attacker's attack&quot;</a> section to learn more about why you might want to use
 * multiple hash iterations.
 * <h2>MD5 &amp; SHA-1 Notice</h2>
 * <a href="http://en.wikipedia.org/wiki/MD5">MD5</a> and
 * <a href="http://en.wikipedia.org/wiki/SHA_hash_functions">SHA-1</a> algorithms are now known to be vulnerable to
 * compromise and/or collisions (read the linked pages for more).  While most applications are ok with either of these
 * two, if your application mandates high security, use the SHA-256 (or higher) hashing algorithms and their
 * supporting {@code CredentialsMatcher} implementations.
 *
 * @see org.apache.shiro.crypto.hash.Md5Hash
 * @see org.apache.shiro.crypto.hash.Sha1Hash
 * @see org.apache.shiro.crypto.hash.Sha256Hash
 * @since 0.9
 */
public class HashedCredentialsMatcher extends SimpleCredentialsMatcher {

    /**
     * @since 1.1
     */
	
	Logger logger = LoggerFactory.getLogger(HashedCredentialsMatcher.class);
	
    private String hashAlgorithmName;
    private int hashIterations;
    private boolean storedCredentialsHexEncoded;
    /**
     * JavaBeans-compatibile no-arg constructor intended for use in IoC/Dependency Injection environments.  If you
     * use this constructor, you <em>MUST</em> also additionally set the
     * {@link #setHashAlgorithmName(String) hashAlgorithmName} property.
     */
    public HashedCredentialsMatcher() {
        this.hashAlgorithmName = null;
        this.hashIterations = 1;
        this.storedCredentialsHexEncoded = true; //false means Base64-encoded
    }

    /**
     * Creates an instance using the specified {@link #getHashAlgorithmName() hashAlgorithmName} to hash submitted
     * credentials.
     * @param hashAlgorithmName the {@code Hash} {@link org.apache.shiro.crypto.hash.Hash#getAlgorithmName() algorithmName}
     *                          to use when performing hashes for credentials matching.
     * @since 1.1
     */
    public HashedCredentialsMatcher(String hashAlgorithmName) {
        this();
        if (!StringUtils.hasText(hashAlgorithmName) ) {
            throw new IllegalArgumentException("hashAlgorithmName cannot be null or empty.");
        }
        this.hashAlgorithmName = hashAlgorithmName;
    }

    
    /**
     * This implementation first hashes the {@code token}'s credentials, potentially using a
     * {@code salt} if the {@code info} argument is a
     * {@link org.apache.shiro.authc.SaltedAuthenticationInfo SaltedAuthenticationInfo}.  It then compares the hash
     * against the {@code AuthenticationInfo}'s
     * {@link #getCredentials(org.apache.shiro.authc.AuthenticationInfo) already-hashed credentials}.  This method
     * returns {@code true} if those two values are {@link #equals(Object, Object) equal}, {@code false} otherwise.
     *
     * @param token the {@code AuthenticationToken} submitted during the authentication attempt.
     * @param info  the {@code AuthenticationInfo} stored in the system matching the token principal
     * @return {@code true} if the provided token credentials hash match to the stored account credentials hash,
     *         {@code false} otherwise
     * @since 1.1
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) 
    {
        Object tokenCredentials = token.getCredentials();		
        Object hashedAccountCredentials = hashAccountCredentials(info);
        
        //将token里的密码与  authcInfo中hash处理后的结果进行匹配
        return equals(tokenCredentials, hashedAccountCredentials);
    }

    /**
     * Hash the provided {@code token}'s credentials using the salt stored with the account if the
     * {@code info} instance is an {@code instanceof} {@link SaltedAuthenticationInfo SaltedAuthenticationInfo} (see
     * the class-level JavaDoc for why this is the preferred approach).
     * <p/>
     * If the {@code info} instance is <em>not</em>
     * an {@code instanceof} {@code SaltedAuthenticationInfo}, the logic will fall back to Shiro 1.0
     * backwards-compatible logic:  it will first check to see {@link #isHashSalted() isHashSalted} and if so, will try
     * to acquire the salt from {@link #getSalt(AuthenticationToken) getSalt(AuthenticationToken)}.  See the class-level
     * JavaDoc for why this is not recommended.  This 'fallback' logic exists only for backwards-compatibility.
     * {@code Realm}s should be updated as soon as possible to return {@code SaltedAuthenticationInfo} instances
     * if account credentials salting is enabled (highly recommended for password-based systems).
     *
     * @param token the submitted authentication token from which its credentials will be hashed
     * @param info  the stored account data, potentially used to acquire a salt
     * @return the token credentials hash
     * @since 1.1
     */
    protected Object hashAccountCredentials(AuthenticationInfo info) {
        
    	Object salt = null;
        if (info instanceof SaltedAuthenticationInfo) {
            salt = ((SaltedAuthenticationInfo) info).getCredentialsSalt();
        }
        
        //对 authcInfo中的密码进行加盐hash处理，盐值取之前生成authcInfo时去数据库取的动态盐，默认迭代次数1
        Hash computed = hashAccountCredentials(info.getCredentials(), salt, getHashIterations());
        return ByteSource.Util.bytes(computed.toHex());
    }

    /**
     * Hashes the provided credentials a total of {@code hashIterations} times, using the given salt.  The hash
     * implementation/algorithm used is based on the {@link #getHashAlgorithmName() hashAlgorithmName} property.
     *
     * @param credentials    the submitted authentication token's credentials to hash
     * @param salt           the value to salt the hash, or {@code null} if a salt will not be used.
     * @param hashIterations the number of times to hash the credentials.  At least one hash will always occur though,
     *                       even if this argument is 0 or negative.
     * @return the hashed value of the provided credentials, according to the specified salt and hash iterations.
     */
    protected Hash hashAccountCredentials(Object credentials, Object salt, int hashIterations) {
        String hashAlgorithmName = assertHashAlgorithmName();
        return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
    }

    /**
     * Returns the {@link #getHashAlgorithmName() hashAlgorithmName} property, but will throw an
     * {@link IllegalStateException} if it has not been set.
     *
     * @return the required {@link #getHashAlgorithmName() hashAlgorithmName} property
     * @throws IllegalStateException if the property has not been set prior to calling this method.
     * @since 1.1
     */
    private String assertHashAlgorithmName() throws IllegalStateException {
    	String hashAlgorithmName = getHashAlgorithmName();
    	if (hashAlgorithmName == null) {
    		String msg = "Required 'hashAlgorithmName' property has not been set.  This is required to execute " +
    				"the hashing algorithm.";
    		throw new IllegalStateException(msg);
    	}
    	return hashAlgorithmName;
    }
    
    /**
     * Returns the {@code Hash} {@link org.apache.shiro.crypto.hash.Hash#getAlgorithmName() algorithmName} to use
     * when performing hashes for credentials matching.
     *
     * @return the {@code Hash} {@link org.apache.shiro.crypto.hash.Hash#getAlgorithmName() algorithmName} to use
     *         when performing hashes for credentials matching.
     * @since 1.1
     */
    public String getHashAlgorithmName() {
        return hashAlgorithmName;
    }

    /**
     * Sets the {@code Hash} {@link org.apache.shiro.crypto.hash.Hash#getAlgorithmName() algorithmName} to use
     * when performing hashes for credentials matching.
     *
     * @param hashAlgorithmName the {@code Hash} {@link org.apache.shiro.crypto.hash.Hash#getAlgorithmName() algorithmName}
     *                          to use when performing hashes for credentials matching.
     * @since 1.1
     */
    public void setHashAlgorithmName(String hashAlgorithmName) {
        this.hashAlgorithmName = hashAlgorithmName;
    }

    /**
     * Returns {@code true} if the system's stored credential hash is Hex encoded, {@code false} if it
     * is Base64 encoded.
     * <p/>
     * Default value is {@code true} for convenience - all of Shiro's {@link Hash Hash#toString()}
     * implementations return Hex encoded values by default, making this class's use with those implementations
     * easier.
     *
     * @return {@code true} if the system's stored credential hash is Hex encoded, {@code false} if it
     *         is Base64 encoded.  Default is {@code true}
     */
    public boolean isStoredCredentialsHexEncoded() {
        return storedCredentialsHexEncoded;
    }

    /**
     * Sets the indicator if this system's stored credential hash is Hex encoded or not.
     * <p/>
     * A value of {@code true} will cause this class to decode the system credential from Hex, a
     * value of {@code false} will cause this class to decode the system credential from Base64.
     * <p/>
     * Unless overridden via this method, the default value is {@code true} for convenience - all of Shiro's
     * {@link Hash Hash#toString()} implementations return Hex encoded values by default, making this class's use with
     * those implementations easier.
     *
     * @param storedCredentialsHexEncoded the indicator if this system's stored credential hash is Hex
     *                                    encoded or not ('not' automatically implying it is Base64 encoded).
     */
    public void setStoredCredentialsHexEncoded(boolean storedCredentialsHexEncoded) {
        this.storedCredentialsHexEncoded = storedCredentialsHexEncoded;
    }

    /**
     * Returns the number of times a submitted {@code AuthenticationToken}'s credentials will be hashed before
     * comparing to the credentials stored in the system.
     * <p/>
     * Unless overridden, the default value is {@code 1}, meaning a normal hash execution will occur.
     *
     * @return the number of times a submitted {@code AuthenticationToken}'s credentials will be hashed before
     *         comparing to the credentials stored in the system.
     */
    public int getHashIterations() {
        return hashIterations;
    }

    /**
     * Sets the number of times a submitted {@code AuthenticationToken}'s credentials will be hashed before comparing
     * to the credentials stored in the system.
     * <p/>
     * Unless overridden, the default value is {@code 1}, meaning a normal single hash execution will occur.
     * <p/>
     * If this argument is less than 1 (i.e. 0 or negative), the default value of 1 is applied.  There must always be
     * at least 1 hash iteration (otherwise there would be no hash).
     *
     * @param hashIterations the number of times to hash a submitted {@code AuthenticationToken}'s credentials.
     */
    public void setHashIterations(int hashIterations) {
        if (hashIterations < 1) {
            this.hashIterations = 1;
        } else {
            this.hashIterations = hashIterations;
        }
    }

}

